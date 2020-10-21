package io.iljapavlovs.cdc.springcloudcontractdemo.consumer.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.MDC
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.MessagePostProcessor
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.UUID

const val PRODUCER_EXTERNAL_EXCHANGE = "producer.external"
const val CONSUMER_QUEUE = "consumer.queue"

const val ROUTING_KEY_MAPPER = "*.CREATED"

const val DEFAULT_JSON_CONTAINER_FACTORY = "rabbitJsonSerializationListenerContainerFactory"

@Configuration
class RabbitMqProducerConfig(
    private val objectMapper: ObjectMapper
) {

    @Bean
    fun producerExternalExchange(): Exchange {
        return TopicExchange(PRODUCER_EXTERNAL_EXCHANGE)
    }

    @Bean
    fun queue(): Queue {
        return Queue(CONSUMER_QUEUE)
    }

    @Bean
    fun binding(queue: Queue, eventExchange: Exchange): Binding = BindingBuilder
        .bind(queue)
        .to(eventExchange)
        .with(ROUTING_KEY_MAPPER)
        .noargs()


    @Bean(DEFAULT_JSON_CONTAINER_FACTORY)
    fun rabbitJsonSerializationListenerContainerFactory(
        configurer: SimpleRabbitListenerContainerFactoryConfigurer,
        connectionFactory: ConnectionFactory
    ): SimpleRabbitListenerContainerFactory =
        SimpleRabbitListenerContainerFactory()
            .apply {
                configurer.configure(this, connectionFactory)
                setAfterReceivePostProcessors(afterReceivePostProcessor())
                setMessageConverter(Jackson2JsonMessageConverter(objectMapper))
            }

    private fun afterReceivePostProcessor() =
        MessagePostProcessor {
            it.apply {
                MDC.put("nested_trace", UUID.randomUUID().toString())
            }
        }
}