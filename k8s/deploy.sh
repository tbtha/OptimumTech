#!/bin/bash
# Script de despliegue en Kubernetes (Docker Desktop)
# Uso: ./k8s/deploy.sh

set -e  # detiene el script si hay algun error

echo "========================================"
echo " OptimumTech - Despliegue en Kubernetes"
echo "========================================"

# 1. Verificar que kubectl está conectado
echo ""
echo "[1/5] Verificando conexion con el cluster..."
kubectl cluster-info --request-timeout=5s
echo "✅ Cluster disponible"

# 2. Crear namespace
echo ""
echo "[2/5] Creando namespace 'optimumtech'..."
kubectl apply -f k8s/namespace.yaml
echo "✅ Namespace listo"

# 3. Desplegar MySQL (secret + deployment + service)
echo ""
echo "[3/5] Desplegando MySQL..."
kubectl apply -f k8s/mysql/secret.yaml
kubectl apply -f k8s/mysql/deployment.yaml
kubectl apply -f k8s/mysql/service.yaml
echo "⏳ Esperando que MySQL esté listo..."
kubectl wait --namespace=optimumtech \
  --for=condition=ready pod \
  --selector=app=mysql \
  --timeout=120s
echo "✅ MySQL listo"

# 4. Desplegar microservicios
echo ""
echo "[4/5] Desplegando microservicios..."
kubectl apply -f k8s/api_course/deployment.yaml
kubectl apply -f k8s/api_course/service.yaml

kubectl apply -f k8s/api_user/deployment.yaml
kubectl apply -f k8s/api_user/service.yaml

kubectl apply -f k8s/api_payment/deployment.yaml
kubectl apply -f k8s/api_payment/service.yaml

kubectl apply -f k8s/api_report/deployment.yaml
kubectl apply -f k8s/api_report/service.yaml

echo "✅ Microservicios aplicados"

# 5. Desplegar Gateway
echo ""
echo "[5/5] Desplegando Gateway..."
kubectl apply -f k8s/gateway/deployment.yaml
kubectl apply -f k8s/gateway/service.yaml
echo "✅ Gateway aplicado"

# Resumen final
echo ""
echo "========================================"
echo " Estado del despliegue"
echo "========================================"
kubectl get all -n optimumtech

echo ""
echo "Gateway disponible en: http://localhost:30000"
echo "Para ver logs: kubectl logs -n optimumtech deploy/<nombre>"
echo "Para eliminar todo: kubectl delete namespace optimumtech"
