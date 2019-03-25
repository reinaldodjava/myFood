FROM java:alpine
ADD eureka-1.0.0.jar eureka.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/eureka.jar"]
