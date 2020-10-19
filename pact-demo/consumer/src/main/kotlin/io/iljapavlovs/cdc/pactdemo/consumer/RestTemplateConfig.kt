package io.iljapavlovs.cdc.pactdemo.consumer

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig(
){
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}