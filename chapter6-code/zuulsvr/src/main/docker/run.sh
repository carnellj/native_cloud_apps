#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start                 *"
echo "********************************************************"
while ! `nc -z eurekaserver 8761`; do sleep 3; done
echo "******* Eureka Server has started"


echo "********************************************************"
echo "Starting Zuul Service  "
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -jar /usr/local/zuulservice/zuulsvr-0.0.1-SNAPSHOT.jar