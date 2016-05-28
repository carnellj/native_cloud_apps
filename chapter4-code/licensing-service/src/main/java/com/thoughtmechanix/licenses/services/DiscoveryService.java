package com.thoughtmechanix.licenses.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiscoveryService {
    @Autowired
    private DiscoveryClient discoveryClient;

    public List getEurekaServices(){
       List<String> services = new ArrayList<String>();

        discoveryClient.getServices().forEach(serviceName -> {
            discoveryClient.getInstances(serviceName).forEach(instance->{
                services.add( String.format("%s--> %s:%s",serviceName,instance.getHost(), instance.getPort()));
            });
        });

        return services;
    }
}
