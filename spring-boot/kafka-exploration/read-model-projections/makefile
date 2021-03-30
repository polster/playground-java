#!make

APP_NAME="projections-v1"
DOCKER_NETWORK="localdev_default"

maven-build:
	mvn clean install

docker-build:
	docker build -t ${APP_NAME} .

docker-run:
	docker run -d -p 8083:8080 --network ${DOCKER_NETWORK} --name ${APP_NAME} ${APP_NAME}

docker-terminate:
	docker stop ${APP_NAME}; docker rm ${APP_NAME} | true

docker-deploy: maven-build docker-build docker-terminate docker-run

.PHONY: maven-build docker-build docker-run docker-deploy docker-terminate