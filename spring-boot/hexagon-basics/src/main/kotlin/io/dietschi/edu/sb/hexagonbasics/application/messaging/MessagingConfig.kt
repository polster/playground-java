package io.dietschi.edu.sb.hexagonbasics.application.messaging

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class MessagingConfig {

    @Bean
    fun queueMessagingTemplate(sqsClient: AmazonSQSAsync): QueueMessagingTemplate = QueueMessagingTemplate(sqsClient)

    @Bean
    @Primary
    fun sqsClient(messagingClientProperties: MessagingClientProperties): AmazonSQSAsync = AmazonSQSAsyncClientBuilder
        .standard()
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration(
                messagingClientProperties.endpointUrlSns,
                messagingClientProperties.region
            )
        )
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(
                    messagingClientProperties.credentials.accessKey,
                    messagingClientProperties.credentials.accessKey
                )
            )
        )
        .build()
}