FROM openjdk:latest
RUN  apt-get clean && apt-get update && apt-get install -y netcat
RUN mkdir -p /usr/local/organizationservice
ADD organization-service-0.0.1-SNAPSHOT.jar /usr/local/organizationservice/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
