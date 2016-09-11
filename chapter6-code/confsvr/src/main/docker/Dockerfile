FROM openjdk:latest
RUN  apt-get update && apt-get install -y netcat
RUN mkdir -p /usr/local/configserver
ADD configurationserver-0.0.1-SNAPSHOT.jar /usr/local/configserver/
EXPOSE 8888 8889 9000 9001
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
