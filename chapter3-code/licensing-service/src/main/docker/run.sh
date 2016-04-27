#!/bin/sh


echo "********************************************************"
echo "Waiting for the configuration server to start"         *
echo "********************************************************"
while ! `nc -z configserver 8888`; do sleep 3; done
echo ">>>>>>>>>>>> Configuration Server has started"

echo "********************************************************"
echo "Waiting for the database server to start"              *
echo "********************************************************"
while ! `nc -z database 5432`; do sleep 3; done
echo ">>>>>>>>>>>> Database Server has started"


echo "********************************************************"
echo "Starting License Server with Configuration Service :  $CONFIGSERVER_URI";
echo "********************************************************"
java -Dspring.cloud.config.uri=$CONFIGSERVER_URI -Dspring.profiles.active=$PROFILE -jar /usr/local/licensingservice/licensing-service-0.0.1-SNAPSHOT.jar