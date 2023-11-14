FROM gradle:8.4.0-jdk17 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle build
RUN ls -la /app/target/libs

FROM eclipse-temurin:17
COPY --from=build /app/target/libs/usermicroservice-1.0.jar /app/app.jar
WORKDIR /app
RUN ls /app
ENTRYPOINT ["java", "-jar", "app.jar"]