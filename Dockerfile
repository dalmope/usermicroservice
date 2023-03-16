FROM amazoncorretto:17-alpine-jdk
COPY target/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]