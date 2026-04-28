#!/bin/zsh

docker network create system-net

docker run -d --name postgres --network system-net \
  -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=AdminAdmin \
  -v pgdata:/var/lib/postgresql/data \
  my-postgres

docker run -d --name user-service --network system-net -p 8082:8080 \
  -e SERVER_PORT=8080 \
  -e DB_URL=jdbc:postgresql://postgres:5432/user-db \
  -e DB_USER=admin -e DB_PASSWORD=AdminAdmin \
  my-user

docker run -d --name currency-service --network system-net -p 8084:8080 \
  -e SERVER_PORT=8080 \
  -e DB_URL=jdbc:postgresql://postgres:5432/currency-db \
  -e DB_USER=admin -e DB_PASSWORD=AdminAdmin \
  my-currency

docker run -d --name expense-service --network system-net -p 8083:8080 \
  -e SERVER_PORT=8080 \
  -e DB_URL=jdbc:postgresql://postgres:5432/expense-db \
  -e DB_USER=admin -e DB_PASSWORD=AdminAdmin \
  -e USER_URL=http://user-service:8080 \
  -e CURRENCY_URL=http://currency-service:8080 \
  my-expense

docker run -d --name auth-service --network system-net -p 8085:8080 \
  -e SERVER_PORT=8080 \
  -e DB_URL=jdbc:postgresql://postgres:5432/user-db \
  -e DB_USER=admin -e DB_PASSWORD=AdminAdmin \
  my-auth

docker run -d --name healthcheck-service --network system-net -p 8081:8080 \
  -e SERVER_PORT=8080 \
  my-healthcheck

docker run -d --name gateway --network system-net -p 8080:8080 \
  -e SERVER_PORT=8080 \
  -e HEALTHCHECK_URL=http://healthcheck-service:8080 \
  -e USER_URL=http://user-service:8080 \
  -e EXPENSE_URL=http://expense-service:8080 \
  -e CURRENCY_URL=http://currency-service:8080 \
  -e AUTH_URL=http://auth-service:8080 \
  my-gateway
