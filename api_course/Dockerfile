# Etapa 1: Build
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY pom.xml ./
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests -pl api_course -am

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/api_course/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]