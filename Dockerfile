# ---------- STAGE 1: BUILD  ----------
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

COPY mvnw pom.xml ./
COPY .mvn .mvn

RUN ./mvnw -q dependency:go-offline

COPY src src
RUN ./mvnw -q package -DskipTests


# ---------- STAGE 2: RUN  ----------
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
