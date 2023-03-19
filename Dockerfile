FROM amazoncorretto:17-alpine-jdk AS build
RUN apk add --no-cache gradle
COPY . /app
WORKDIR /app
RUN gradle build

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /app/target/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]