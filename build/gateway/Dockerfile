FROM java:alpine
ADD gateway-1.0.0.jar gateway.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/gateway.jar"]
