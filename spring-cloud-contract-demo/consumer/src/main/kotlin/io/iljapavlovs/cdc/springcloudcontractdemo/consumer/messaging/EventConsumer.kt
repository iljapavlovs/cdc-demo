package io.iljapavlovs.cdc.springcloudcontractdemo.consumer.messaging

import io.iljapavlovs.cdc.springcloudcontractdemo.consumer.PersonMessage
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

//@Profile("rabbit")
@Component
class EventConsumer(

) {
    val createdPersons = mutableListOf<PersonMessage>()

    @RabbitListener(
        queues = [CONSUMER_QUEUE],
        containerFactory = DEFAULT_JSON_CONTAINER_FACTORY
    )
    fun consumeFailedTransfer(
//        @Header("trace_id") traceId: String,
        @Payload payload: PersonMessage
    ) {
        println("Received message : $payload")
        createdPersons.add(payload)
    }

//    fun getCreatedPersons() = createdPersons
}