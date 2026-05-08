kubectl create secret generic db-secret \
  --from-literal=postgres-password=AdminAdmin \
  --from-literal=postgres-user=admin