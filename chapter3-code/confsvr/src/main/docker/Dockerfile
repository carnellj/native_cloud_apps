FROM openjdk:latest
RUN mkdir -p /usr/local/configserver
ADD configurationserver-0.0.1-SNAPSHOT.jar /usr/local/configserver/
EXPOSE 8888
ENTRYPOINT ["java","-jar","/usr/local/configserver/configurationserver-0.0.1-SNAPSHOT.jar"]
