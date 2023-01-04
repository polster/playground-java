#!/bin/bash

# Requires:
#
# AWS_ACCESS_KEY_ID
# AWS_SECRET_ACCESS_KEY
# AWS_DEFAULT_REGION

SCRIPT_PATH="$(dirname "$0")"

SNS_ENDPOINT="http://localhost:4566"
SNS_IN_TOPIC="arn:aws:sns:eu-central-1:000000000000:hex-order-commands"
MESSAGE_PAYLOAD=$(cat $SCRIPT_PATH/add-product.json)

function error {
    echo -e "${1}"
    exit 1
}

order_id=${1}
[[ -z $order_id ]] && error "Order ID not defined, aborting!"

MESSAGE_PAYLOAD=${MESSAGE_PAYLOAD/ORDER_ID/$order_id}

aws --endpoint-url=${SNS_ENDPOINT} \
    sns publish \
    --topic-arn ${SNS_IN_TOPIC} \
    --message-attributes '{"messageType":{"DataType":"String","StringValue":"add-product"}}' \
    --message "${MESSAGE_PAYLOAD}"