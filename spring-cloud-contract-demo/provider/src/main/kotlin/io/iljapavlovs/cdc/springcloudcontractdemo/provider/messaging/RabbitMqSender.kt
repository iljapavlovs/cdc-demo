package io.iljapavlovs.cdc.springcloudcontractdemo.provider.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import io.iljapavlovs.cdc.springcloudcontractdemo.provider.PersonMessage
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMqSender(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper
) {

    fun sendPersonCreated(message: PersonMessage) {
        rabbitTemplate.convertAndSend(
            PRODUCER_EXTERNAL_EXCHANGE,
            ROUTING_KEY,
            message,
            defaultMessagePostProcessor()
        )
    }

    private fun defaultMessagePostProcessor(addition: (Message.() -> Unit) = {}): (Message) -> Message {
        return {
            it.apply {
                addition()
                this.messageProperties.headers.plusAssign("trace_id" to "")
            }
        }
    }
}