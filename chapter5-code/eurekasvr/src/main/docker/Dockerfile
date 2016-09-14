FROM openjdk:latest
RUN mkdir -p /usr/local/eurekaserver
ADD eurekasvr-0.0.1-SNAPSHOT.jar /usr/local/eurekaserver/
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/eurekaserver/eurekasvr-0.0.1-SNAPSHOT.jar"]
