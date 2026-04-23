# -- Build

FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# -- Run
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /product.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/product.jar"]


