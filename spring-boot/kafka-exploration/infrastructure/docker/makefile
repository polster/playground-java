#!make

KAFKA_BOOTSTRAP_SERVER=localhost:9092
KAFKA_TOPICS_FILE=./config/kafka/topics.txt

kafka-topics-list:
	kafka-topics.sh \
		--bootstrap-server ${KAFKA_BOOTSTRAP_SERVER} \
		--list

kafka-topics-create:
	awk -F':' '{ system("kafka-topics.sh --create --bootstrap-server ${KAFKA_BOOTSTRAP_SERVER} --topic="$$1" --partitions="$$2" --replication-factor="$$3" --config="$$4) }' \
		${KAFKA_TOPICS_FILE}

kafka-topics-delete:
	kafka-topics.sh \
    		--delete \
    		--bootstrap-server ${KAFKA_BOOTSTRAP_SERVER} \
    		--topic __consumer_offsets
	awk -F':' '{ system("kafka-topics.sh --delete --bootstrap-server ${KAFKA_BOOTSTRAP_SERVER} --topic="$$1) }' \
		${KAFKA_TOPICS_FILE}

kafka-topic-delete:
	kafka-topics.sh \
		--delete \
		--bootstrap-server ${KAFKA_BOOTSTRAP_SERVER} \
		--topic ${topic}

.PHONY: kafka-topics-list \
	kafka-topics-delete \
	kafka-topics-create \
	kafka-topic-delete