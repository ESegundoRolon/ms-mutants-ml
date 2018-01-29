FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD ${outputDirectory}/${JAR_FILE} /app.jar
RUN sh -c 'touch /app.jar'
ADD ${outputDirectory}/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENV JAVA_OPTS=""
ENTRYPOINT ["/entrypoint.sh"]