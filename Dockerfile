FROM openjdk:11

LABEL maintainer="dinneyacharles@gmail.com"

# create work directory
RUN mkdir -p /usr/serviceMonitor

WORKDIR /usr/serviceMonitor

ADD build/libs/servicemonitor-0.0.1-SNAPSHOT.jar .

EXPOSE 8071

ENTRYPOINT [ "java", "-jar", "servicemonitor-0.0.1-SNAPSHOT.jar"]