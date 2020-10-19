package io.iljapavlovs.cdc.springcloudcontractdemo.consumer

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import org.springframework.web.client.RestTemplate
import java.net.URI

@Validated
@ConstructorBinding
@ConfigurationProperties("provider")
data class ProviderProperties(
    val uri: String
)

@Configuration
@EnableConfigurationProperties(ProviderProperties::class)
class ProviderConfig