package io.iljapavlovs.cdc.springcloudcontractdemo.provider.messaging

import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

const val PRODUCER_EXTERNAL_EXCHANGE = "producer.external"
const val ROUTING_KEY = "PERSON.CREATED"

@Configuration
class RabbitMqProducerConfig(
) {
    /**
     * TODO so then Binding of Exchange and Queues are done on the Consumer side?
     */
    @Bean
    fun producerExternalExchange(): Exchange {
        return TopicExchange(PRODUCER_EXTERNAL_EXCHANGE)
    }
}