package io.iljapavlovs.cdc.springcloudcontractdemo.provider.messaging

import io.iljapavlovs.cdc.springcloudcontractdemo.provider.PersonMessage
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class EventTriggerController(
    private val rabbitMqSender: RabbitMqSender
) {

    @RequestMapping("/trigger-event")
    @ResponseBody
    fun triggerEvent(): ResponseEntity<Unit> {
        rabbitMqSender.sendPersonCreated(
            PersonMessage(
                name = "Ivan",
                ssn = "111"
//                eventTime = LocalDateTime.now()
            )
        )
        return ResponseEntity.status(200).build()
    }
}