FROM amazoncorretto:17-alpine-jdk
RUN gradle build
COPY target/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]