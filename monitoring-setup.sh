helm repo add grafana https://grafana.github.io/helm-charts &&
  helm repo update &&
  helm upgrade --install --rollback-on-failure --timeout 300s grafana-k8s-monitoring grafana/k8s-monitoring \
    --version "^4" --namespace "default" --create-namespace --values - <<'EOF'
cluster:
  name: devops-labs
destinations:
  grafana-cloud-metrics:
    type: prometheus
    url: https://prometheus-prod-65-prod-eu-west-2.grafana.net./api/prom/push
    auth:
      type: basic
      username: "3181278"
      password: glc_eyJvIjoiMTc2MDAxMSIsIm4iOiJzdGFjay0xNjI4NTI3LWludGVncmF0aW9uLWRldm9wcy1sYWJzLWRldm9wcy1sYWJzIiwiayI6ImJtNXU1MTY0b285R2Q0NUplYzE3clNWbSIsIm0iOnsiciI6InByb2QtZXUtd2VzdC0yIn19
  grafana-cloud-logs:
    type: loki
    url: https://logs-prod-012.grafana.net./loki/api/v1/push
    auth:
      type: basic
      username: "1586314"
      password: glc_eyJvIjoiMTc2MDAxMSIsIm4iOiJzdGFjay0xNjI4NTI3LWludGVncmF0aW9uLWRldm9wcy1sYWJzLWRldm9wcy1sYWJzIiwiayI6ImJtNXU1MTY0b285R2Q0NUplYzE3clNWbSIsIm0iOnsiciI6InByb2QtZXUtd2VzdC0yIn19
clusterMetrics:
  enabled: true
  collector: alloy-metrics
hostMetrics:
  enabled: true
  collector: alloy-metrics
  linuxHosts:
    enabled: true
  windowsHosts:
    enabled: false
costMetrics:
  enabled: false
  collector: alloy-metrics
clusterEvents:
  enabled: true
  collector: alloy-singleton
podLogsViaLoki:
  enabled: true
  collector: alloy-logs
collectors:
  alloy-metrics:
    presets:
      - clustered
      - statefulset
  alloy-singleton:
    presets:
      - singleton
  alloy-logs:
    presets:
      - filesystem-log-reader
      - daemonset
collectorCommon:
  alloy:
    remoteConfig:
      enabled: true
      url: https://fleet-management-prod-011.grafana.net
      auth:
        type: basic
        username: "1628527"
        password: glc_eyJvIjoiMTc2MDAxMSIsIm4iOiJzdGFjay0xNjI4NTI3LWludGVncmF0aW9uLWRldm9wcy1sYWJzLWRldm9wcy1sYWJzIiwiayI6ImJtNXU1MTY0b285R2Q0NUplYzE3clNWbSIsIm0iOnsiciI6InByb2QtZXUtd2VzdC0yIn19
telemetryServices:
  kube-state-metrics:
    deploy: true
  node-exporter:
    deploy: true
  windows-exporter:
    deploy: false
  opencost:
    deploy: false
EOF