package io.dietschi.edu.sb.hexagonbasics.application.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "messaging.aws.client")
@ConstructorBinding
data class MessagingClientProperties(
    val endpointUrlSqs: String,
    val endpointUrlSns: String,
    val region: String,
    val credentials: Credentials
) {

    @ConstructorBinding
    data class Credentials(
        val accessKey: String,
        val secretKey: String
    )
}