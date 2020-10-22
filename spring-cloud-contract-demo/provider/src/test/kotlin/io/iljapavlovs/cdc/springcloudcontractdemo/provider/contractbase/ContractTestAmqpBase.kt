package io.iljapavlovs.cdc.springcloudcontractdemo.provider.contractbase

import io.iljapavlovs.cdc.springcloudcontractdemo.provider.PersonMessage
import io.iljapavlovs.cdc.springcloudcontractdemo.provider.messaging.RabbitMqSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier


@SpringBootTest(properties = ["stubrunner.amqp.enabled=true"])
//denoting a base class for integration tests
@AutoConfigureMessageVerifier
abstract class ContractTestAmqpBase {
    @Autowired
    private lateinit var rabbitMqSender: RabbitMqSender

    protected fun onPersonCreated() {
        rabbitMqSender.sendPersonCreated(
            PersonMessage(
                name = "Ivan",
                ssn = "111"
            )
        )
    }
}





