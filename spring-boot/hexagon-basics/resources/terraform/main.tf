provider "aws" {
  access_key                  = "34gasdf3"
  secret_key                  = "432fgasd"
  region                      = "eu-central-1"
  skip_credentials_validation = true
  skip_metadata_api_check     = true
  skip_requesting_account_id  = true

  endpoints {
    sns            = "http://localhost:4566"
    sqs            = "http://localhost:4566"
  }
}

########
# Topics
########

resource "aws_sns_topic" "hex-order-events" {
  name = "hex-order-events"
}

resource "aws_sns_topic" "hex-order-commands" {
  name = "hex-order-commands"
}

########
# Queues
########

resource "aws_sqs_queue" "hex-order-commands-new-order" {
  name = "hex-order-commands-new-order"
  fifo_queue = false
  redrive_policy = jsonencode(
    {
      "deadLetterTargetArn": aws_sqs_queue.hex-order-commands-dlq.arn,
      "maxReceiveCount": 3
    }
  )
}

resource "aws_sqs_queue" "hex-order-commands-add-product" {
  name = "hex-order-commands-add-product"
  fifo_queue = false
  redrive_policy = jsonencode(
    {
      "deadLetterTargetArn": aws_sqs_queue.hex-order-commands-dlq.arn,
      "maxReceiveCount": 3
    }
  )
}

resource "aws_sqs_queue" "hex-order-commands-dlq" {
  name = "hex-order-commands-dlq"
}

###############
# Subscriptions
###############

resource "aws_sns_topic_subscription" "hex-order-commands-subscription1" {
  topic_arn = aws_sns_topic.hex-order-commands.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.hex-order-commands-add-product.arn
  raw_message_delivery = true
  filter_policy = jsonencode(
    {
      "messageType": [
        "add-product"
      ]
    }
  )
}

resource "aws_sns_topic_subscription" "hex-order-commands-subscription2" {
  topic_arn = aws_sns_topic.hex-order-commands.arn
  protocol  = "sqs"
  endpoint  = aws_sqs_queue.hex-order-commands-new-order.arn
  raw_message_delivery = true
  filter_policy = jsonencode(
    {
      "messageType": [
        "new-order"
      ]
    }
  )
}

##########
# Policies
##########

resource "aws_sqs_queue_policy" "hex-order-commands-new-order" {
  queue_url = aws_sqs_queue.hex-order-commands-new-order.id
  policy    =   jsonencode(
    {
      "Version":"2012-10-17",
      "Statement":[
        {
          "Effect" : "Allow",
          "Principal":"*",
          "Action":"sqs:*",
          "Resource": aws_sqs_queue.hex-order-commands-new-order.arn
        }
      ]
    }
  )
}

resource "aws_sqs_queue_policy" "hex-order-commands-add-product" {
  queue_url = aws_sqs_queue.hex-order-commands-add-product.id
  policy    =   jsonencode(
    {
      "Version":"2012-10-17",
      "Statement":[
        {
          "Effect" : "Allow",
          "Principal":"*",
          "Action":"sqs:*",
          "Resource": aws_sqs_queue.hex-order-commands-add-product.arn
        }
      ]
    }
  )
}

resource "aws_sns_topic_policy" "hex-order-events" {
  arn    = aws_sns_topic.hex-order-events.arn
  policy    =   jsonencode(
    {
      "Version":"2012-10-17",
      "Statement":[
        {
          "Effect" : "Allow",
          "Principal": "*",
          "Action":"sns:*",
          "Resource":aws_sns_topic.hex-order-events.arn
        }
      ]
    }
  )
}

resource "aws_sns_topic_policy" "hex-order-commands" {
  arn    = aws_sns_topic.hex-order-commands.arn
  policy    =   jsonencode(
    {
      "Version":"2012-10-17",
      "Statement":[
        {
          "Effect" : "Allow",
          "Principal": "*",
          "Action":"sns:*",
          "Resource":aws_sns_topic.hex-order-commands.arn
        }
      ]
    }
  )
}