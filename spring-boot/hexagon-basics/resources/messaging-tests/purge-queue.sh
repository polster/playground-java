#!/bin/bash

# Requires:
#
# AWS_ACCESS_KEY_ID
# AWS_SECRET_ACCESS_KEY
# AWS_DEFAULT_REGION

SCRIPT_PATH="$(dirname "$0")"

SQS_ENDPOINT="http://localhost:4566"
AWS_ACCOUNT="000000000000"

queue_name=$1
queue_url=${SQS_ENDPOINT}/${AWS_ACCOUNT}/${queue_name}
echo "Purging queue with URL: ${queue_url}"

aws --endpoint-url=${SQS_ENDPOINT} \
    sqs purge-queue \
    --queue-url ${queue_url}




aws --endpoint-url "http://localhost:4566" sns set-subscription-attributes --subscription-arn "arn:aws:sns:eu-central-1:000000000000:hex-order-commands:ee8cbcd4-0867-455d-885d-4659aa102605" --attribute-name FilterPolicy --attribute-value '{"store":["example_corp"],"event":["order_placed"]}'