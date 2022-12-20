package io.dietschi.edu.sb.hexagonbasics.application.messaging

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver

@Configuration
class MessagingConfig {

    @Bean
    fun queueMessagingTemplate(sqsClient: AmazonSQSAsync): QueueMessagingTemplate = QueueMessagingTemplate(sqsClient)

    @Bean
    fun queueMessageHandlerFactory(sqsClient: AmazonSQSAsync,
                                   mapper: ObjectMapper): QueueMessageHandlerFactory {

        val queueMessageHandlerFactory = QueueMessageHandlerFactory()
        queueMessageHandlerFactory.setAmazonSqs(sqsClient)
        queueMessageHandlerFactory.setArgumentResolvers(
            listOf(
                PayloadMethodArgumentResolver(
                    jackson2MessageConverter(mapper)
                )
            )
        )
        return queueMessageHandlerFactory
    }

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

    private fun jackson2MessageConverter(mapper: ObjectMapper): MessageConverter {

        val converter = MappingJackson2MessageConverter()
        converter.objectMapper = mapper

        return converter
    }
}