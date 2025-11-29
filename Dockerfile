FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

COPY . /app

RUN apt-get update && \
    apt-get install -y maven && \
    mvn -q clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=build /app/target/housing-platform-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]