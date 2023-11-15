FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ADD target/spring-ws-client.jar spring-ws-client.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
