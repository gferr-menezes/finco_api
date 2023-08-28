FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/finco_api-0.0.1-SNAPSHOT.jar fincoapi.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar fincoapi.jar
