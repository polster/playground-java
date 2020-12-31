# Infrastructure

> This project assumes minikube to be used for local development - other variants of kubernetes like k3s or even OpenShift, may work as well.

## Get started

### Minishift

* If not already done, install [minishift](https://minikube.sigs.k8s.io/docs/start∏) and [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
* Start a new kubernetes cluster:
  ```bash
  minikube start \
    --memory=4096 \
    --extra-config=apiserver.service-node-port-range=1-65535 \
    -p kafka-exploration
  ```
* Create a new namespace and set as the default (subsequent commands do not need extra namespace specification):
  ```bash
  kubectl create namespace kafka-dev
  kubectl config set-context --current --namespace=kafka-dev

  # Verify
  kubectl config view --minify | grep namespace:
  ```

### Kafka

> Follow below instructions, to setup a local Kafka cluster with Strimzi.

* Apply Strimzi dependencies:
  ```bash
  kubectl apply -f 'https://strimzi.io/install/latest?namespace=kafka-dev'
  ```
* Deploy a simple Kafka cluster:
  ```bash
  kubectl apply -f dev-cluster.yml
  ```
* Wait for the cluster to become ready:
  ```bash
  kubectl wait kafka/dev-cluster --for=condition=Ready --timeout=300s
  ```
* Verify:
  ```bash
  kubectl get k
  ```
* List the available services with their URLs, e.g. to get the exact host:port required to access Kafka:
  ```bash
  minikube service list -p kafka-exploration
  ```

### Apicurio

> Follow below instructions, to setup the Apicurio registry.

* Deploy latest operator:
  ```bash
  curl -sSL https://raw.githubusercontent.com/apicurio/apicurio-registry-operator/master/docs/resources/install.yaml | \
  sed "s/{NAMESPACE}/kafka-dev/g" | kubectl apply -f -
  ```
* Deploy the registry (in this example, using in-memory store):
  ```bash
  kubectl create -f https://raw.githubusercontent.com/apicurio/apicurio-registry-operator/master/docs/resources/example-cr/in-memory.yaml
  ```
* Make the registry accessible from outside of the Kubernetes cluster:
  ```bash
  kubectl apply -f apicurio-service.yml
  ```
* Check and try the URL:
  ```bash
  minikube service --url apicurio-registry -p kafka-exploration -n kafka-dev
  ```

## User Manual

### Destroy the Kafka cluseter

In case you need to reset your Kafka cluster by deleting everything:

```bash
kubectl delete $(kubectl get strimzi -o name)
```

## Troubleshooting

### minikube version upgrade issues

Upgrading the minikube version while having older VMs around, may result into errors like ''Error making standard the default storage class'' or ''controlPlane never updated to vXY''.

In such situation, a complete clean-up may help, being aware that this would destroy any minishift VM:

```bash
minikube delete --all --purge
```