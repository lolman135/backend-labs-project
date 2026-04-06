#!/bin/zsh

REGISTRY="registry.gitlab.com/kkirilll2006/devops-labs"

docker buildx build --platform linux/amd64 -t $REGISTRY/postgres:latest ./postgres --push
docker buildx build --platform linux/amd64 -t $REGISTRY/gateway:latest ./ApiGatewayService --push
docker buildx build --platform linux/amd64 -t $REGISTRY/auth:latest ./AuthService --push
docker buildx build --platform linux/amd64 -t $REGISTRY/currency:latest ./CurrencyService --push
docker buildx build --platform linux/amd64 -t $REGISTRY/expense:latest ./ExpenseService --push
docker buildx build --platform linux/amd64 -t $REGISTRY/healthcheck:latest ./HealthcheckService --push
docker buildx build --platform linux/amd64 -t $REGISTRY/user:latest ./UserService --push
