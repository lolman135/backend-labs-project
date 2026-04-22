#!/bin/zsh

docker buildx create --name multiarch-builder --use || true
docker buildx inspect --bootstrap

REGISTRY="registry.gitlab.com/kkirilll2006/devops-labs"
PLATFORMS="linux/amd64,linux/arm64"

docker buildx build --platform $PLATFORMS -t $REGISTRY/postgres:latest ./postgres --push
docker buildx build --platform $PLATFORMS -t $REGISTRY/gateway:latest ./ApiGatewayService --push
docker buildx build --platform $PLATFORMS -t $REGISTRY/auth:latest ./AuthService --push
docker buildx build --platform $PLATFORMS -t $REGISTRY/currency:latest ./CurrencyService --push
docker buildx build --platform $PLATFORMS -t $REGISTRY/expense:latest ./ExpenseService --push
docker buildx build --platform $PLATFORMS -t $REGISTRY/healthcheck:latest ./HealthcheckService --push
docker buildx build --platform $PLATFORMS -t $REGISTRY/user:latest ./UserService --push