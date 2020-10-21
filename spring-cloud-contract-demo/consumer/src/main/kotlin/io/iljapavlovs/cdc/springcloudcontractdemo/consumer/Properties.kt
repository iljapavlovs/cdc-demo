package io.iljapavlovs.cdc.springcloudcontractdemo.consumer

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated

@Validated
@ConstructorBinding
@ConfigurationProperties("provider")
data class ConsumerProperties(
    val uri: String
)

@Configuration
@EnableConfigurationProperties(ConsumerProperties::class)
class ConsumerConfig