FROM amazoncorretto:17-alpine-jdk
RUN apk add --no-cache gradle
COPY . /app
WORKDIR /app
RUN gradle build
WORKDIR /app/build/libs
ENTRYPOINT ["java","-jar","/app/build/libs/app.jar"]