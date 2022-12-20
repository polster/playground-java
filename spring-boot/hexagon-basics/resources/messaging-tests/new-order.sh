#!/bin/bash

# Requires:
#
# AWS_ACCESS_KEY_ID
# AWS_SECRET_ACCESS_KEY
# AWS_DEFAULT_REGION

SCRIPT_PATH="$(dirname "$0")"

SQS_ENDPOINT="http://localhost:4566"
SQS_IN_QUEUE="http://localhost:4566/000000000000/hex-order-commands"
MESSAGE_PAYLOAD=$(cat $SCRIPT_PATH/new-order.json)

aws --endpoint-url=${SQS_ENDPOINT} \
    sqs send-message \
    --queue-url ${SQS_IN_QUEUE} \
    --message-body "${MESSAGE_PAYLOAD}"