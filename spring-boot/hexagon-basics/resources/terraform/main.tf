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

resource "aws_sns_topic" "hex-order-events" {
  name = "hex-order-events"
}

resource "aws_sqs_queue" "hex-order-commands" {
  name = "hex-order-commands"
  redrive_policy = "{\"deadLetterTargetArn\":\"${aws_sqs_queue.hex-order-commands-dlq.arn}\",\"maxReceiveCount\":3}"
}

resource "aws_sqs_queue" "hex-order-commands-dlq" {
  name = "hex-order-commands-dlq"
}

resource "aws_sqs_queue_policy" "hex-order-commands" {
  queue_url = aws_sqs_queue.hex-order-commands.id
  policy    =   <<POLICY
  {
      "Version":"2012-10-17",
      "Statement":[
          {
              "Effect" : "Allow",
              "Principal":"*",
              "Action":"sqs:*",
              "Resource":"${aws_sqs_queue.hex-order-commands.arn}"
          }
      ]
  }
  POLICY
}

resource "aws_sns_topic_policy" "hex-order-events" {
  arn    = aws_sns_topic.hex-order-events.arn
  policy    =   <<POLICY
  {
      "Version":"2012-10-17",
      "Statement":[
          {
              "Effect" : "Allow",
              "Principal":"*",
              "Action":"sns:*",
              "Resource":"${aws_sns_topic.hex-order-events.arn}"
          }
      ]
  }
  POLICY
}