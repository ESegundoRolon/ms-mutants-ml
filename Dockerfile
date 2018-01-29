FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER esegundorolon "rolon-12@hotmail.com"
VOLUME /tmp
ARG JAR_FILE
ARG ENTRY_POINT
ADD ${JAR_FILE} /app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]