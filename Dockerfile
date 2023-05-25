FROM maven:3.8.1-openjdk-8 AS builder
COPY . /app
WORKDIR /app
RUN mvn clean package

FROM openjdk:8
COPY --from=builder /app/target/SafeCity.jar /app/SafeCity.jar
CMD ["java", "-jar", "/app/SafeCity.jar"]
