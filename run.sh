#!/bin/sh
echo "!========================Run Build==========================!"
echo "!====                  healthcheck                       ===!"
java -jar healthcheck-service.jar &

echo "!====                   currency                         ===!"
java -jar currency-service.jar &

echo "!====                     user                           ===!"
java -jar user-service.jar &

echo "!====                    expense                         ===!"
java -jar expense-service.jar &

echo "!====                  api-gateway                       ===!"
java -jar api-gateway-service.jar

echo "!========================End Build==========================!"
