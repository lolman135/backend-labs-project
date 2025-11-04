FROM gradle:9.1.0-jdk21-alpine AS build

WORKDIR /app

#Services
COPY HealthcheckService HealthcheckService
COPY UserService UserService
COPY ExpenseService ExpenseService
COPY CurrencyService CurrencyService
COPY ApiGatewayService ApiGatewayService

#Gradle
COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .

RUN chmod +x ./gradlew

RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY --from=build /app/HealthcheckService/build/libs/*.jar healthcheck-service.jar
COPY --from=build /app/UserService/build/libs/*.jar user-service.jar
COPY --from=build /app/ExpenseService/build/libs/*.jar expense-service.jar
COPY --from=build /app/CurrencyService/build/libs/*.jar currency-service.jar
COPY --from=build /app/ApiGatewayService/build/libs/*.jar api-gateway-service.jar

COPY run.sh .

EXPOSE 8080

ENTRYPOINT ["sh", "run.sh"]