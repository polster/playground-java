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
  policy    =   <<POLICY
  {
      "Version":"2012-10-17",
      "Statement":[
          {
              "Effect" : "Allow",
              "Principal":"*",
              "Action":"sns:*",
              "Resource":"arn:aws:sqs:*:*:hex-order-events"
          }
      ]
  }
  POLICY
}

resource "aws_sqs_queue" "hex-order-commands" {
  name = "hex-order-commands"
  policy    =   <<POLICY
  {
      "Version":"2012-10-17",
      "Statement":[
          {
              "Effect" : "Allow",
              "Principal":"*",
              "Action":"sqs:*",
              "Resource":"arn:aws:sqs:*:*:hex-order-commands"
          }
      ]
  }
  POLICY
}
