#!makefile

COMPOSE_FILE_INFRA="./resources/docker-compose/local-dev-infra.yml"

docker-infra-up:
	docker-compose -f ${COMPOSE_FILE_INFRA} up -d

docker-infra-down:
	docker-compose -f ${COMPOSE_FILE_INFRA} down

docker-infra-provision-messaging:
	cd ./resources/terraform && \
	terraform init && \
	terraform apply --auto-approve

docker-infra-provision-database:
	./resources/docker-compose/config/mongodb/provision.sh

docker-infra-provision: docker-infra-provision-database docker-infra-provision-messaging

.PHONY: docker-infra-up \
	docker-infra-down \
	docker-infra-provision \
	docker-infra-provision-database \
	docker-infra-provision-messaging