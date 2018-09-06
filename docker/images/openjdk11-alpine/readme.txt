The Dockerfile for openjdk11-alpine is taken from https://github.com/keckelt/openjdk11-alpine.

Use this Dockerfile to create a custom general-purpose docker image of openjdk 11 based on alpine linux.
eg:

docker build --no-cache -t 317/openjdk11-alpine3.8:1.0.0 .

This image may then be used to create application specific docker images from Dockerfiles such as:

FROM 317/openjdk11-alpine3.8:1.0.0
COPY application*.jar application.jar
EXPOSE 8080 8088
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=8088,server=y,suspend=n","-jar","/application.jar"]

followed by:
docker build --no-cache -t 317/application:1.0.0 .