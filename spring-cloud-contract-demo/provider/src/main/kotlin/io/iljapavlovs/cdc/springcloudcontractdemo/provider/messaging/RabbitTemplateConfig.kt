package io.iljapavlovs.cdc.springcloudcontractdemo.provider.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitTemplateConfig(
    private val objectMapper: ObjectMapper
) {

    @Bean
    fun rabbitJsonTemplate(connectionFactory: ConnectionFactory) = RabbitTemplate(connectionFactory)
        .apply {
            messageConverter = Jackson2JsonMessageConverter(objectMapper)
        }
}