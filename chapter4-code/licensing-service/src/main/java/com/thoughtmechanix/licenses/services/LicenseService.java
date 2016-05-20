package com.thoughtmechanix.licenses.services;

import com.thoughtmechanix.licenses.clients.OrganizationClient;
import com.thoughtmechanix.licenses.config.ServiceConfig;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

//    @Autowired
//    private OrganizationClient orgClient;

    @Autowired
    ServiceConfig config;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private void getServiceJSON (String organizationId){
        System.out.println("I am in discovery client");
        ServiceInstance localInstance = discoveryClient.getLocalServiceInstance();
        System.out.println( "Hello World: "+ localInstance.getServiceId()+":"+localInstance.getHost()+":"+localInstance.getPort());

        discoveryClient.getServices().forEach(s -> {
            System.out.println("!!!!!" + s);
        });
    }


    private Organization retrieveOrgInfo(String organizationId){
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://ORGANIZATIONSERVICE/v1/organizations/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        return restExchange.getBody();
    }

    public License getLicense(String organizationId,String licenseId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

       // Organization org = orgClient.getOrganization("organizationId");
        getServiceJSON(organizationId);

        Organization org = retrieveOrgInfo( organizationId);

        System.out.println("!!!! Contact Name:  " + org.getContactName());
        System.out.println("!!!! Contact Phone: " + org.getContactPhone());
        return license.withComment(config.getExampleProperty());
    }

    public List<License> getLicensesByOrg(String organizationId){
        return licenseRepository.findByOrganizationId( organizationId );
    }

    public void saveLicense(License license){
        license.withId( UUID.randomUUID().toString());

        licenseRepository.save(license);

    }

    public void updateLicense(License license){
      licenseRepository.save(license);
    }

    public void deleteLicense(License license){
        licenseRepository.delete( license.getLicenseId());
    }

}
