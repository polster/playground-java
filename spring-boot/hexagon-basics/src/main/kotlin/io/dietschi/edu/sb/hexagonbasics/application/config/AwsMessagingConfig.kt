package io.dietschi.edu.sb.hexagonbasics.application.config

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sns.AmazonSNSAsync
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import io.dietschi.edu.sb.hexagonbasics.application.config.MessagingClientProperties
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver

@Configuration
class AwsMessagingConfig {

    @Bean
    fun queueMessagingTemplate(sqsClient: AmazonSQSAsync): QueueMessagingTemplate =
        QueueMessagingTemplate(sqsClient)

    @Bean
    fun notificationMessagingTemplate(snsClient: AmazonSNSAsync): NotificationMessagingTemplate =
        NotificationMessagingTemplate(snsClient)

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
    fun snsClient(messagingClientProperties: MessagingClientProperties,
                  awsCredentialsProvider: AWSCredentialsProvider): AmazonSNSAsync = AmazonSNSAsyncClientBuilder
        .standard()
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration(
                messagingClientProperties.endpointUrlSns,
                messagingClientProperties.region
            )
        )
        .withCredentials(
            awsCredentialsProvider
        )
        .build()

    @Bean
    @Primary
    fun sqsClient(messagingClientProperties: MessagingClientProperties,
                  awsCredentialsProvider: AWSCredentialsProvider): AmazonSQSAsync = AmazonSQSAsyncClientBuilder
        .standard()
        .withEndpointConfiguration(
            AwsClientBuilder.EndpointConfiguration(
                messagingClientProperties.endpointUrlSqs,
                messagingClientProperties.region
            )
        )
        .withCredentials(
            awsCredentialsProvider
        )
        .build()

    @Bean
    fun awsCredentialsProvider(messagingClientProperties: MessagingClientProperties): AWSCredentialsProvider =
        AWSStaticCredentialsProvider(
            BasicAWSCredentials(
                messagingClientProperties.credentials.accessKey,
                messagingClientProperties.credentials.accessKey
            )
        )

    private fun jackson2MessageConverter(mapper: ObjectMapper): MessageConverter {

        val converter = MappingJackson2MessageConverter()
        converter.objectMapper = mapper

        return converter
    }
}