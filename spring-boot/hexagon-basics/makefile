#!makefile

COMPOSE_FILE_INFRA="./resources/docker-compose/local-dev-infra.yml"

docker-infra-up:
	docker-compose -f ${COMPOSE_FILE_INFRA} up -d

docker-infra-down:
	docker-compose -f ${COMPOSE_FILE_INFRA} down

docker-infra-provision:
	./resources/docker-compose/config/mongodb/provision.sh
	cd ./resources/terraform && \
	terraform init && \
	terraform apply --auto-approve

.PHONY: docker-infra-up \
	docker-infra-down \
	docker-infra-provision