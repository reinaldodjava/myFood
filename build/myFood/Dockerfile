FROM java:alpine
ADD myFood-1.0.0.jar myFood.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/myFood.jar"]
