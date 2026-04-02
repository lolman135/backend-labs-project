#!/bin/zsh

docker build -t my-postgres ./postgres
docker build -t my-gateway ./ApiGatewayService
docker build -t my-auth ./AuthService
docker build -t my-currency ./CurrencyService
docker build -t my-expense ./ExpenseService
docker build -t my-healthcheck ./HealthcheckService
docker build -t my-user ./UserService