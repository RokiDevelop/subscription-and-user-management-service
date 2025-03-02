FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY . ./
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

RUN apt update && apt install -y iputils-ping net-tools netcat && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/target/*.jar subscription_and_user_management_service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/subscription_and_user_management_service.jar"]
