package io.iljapavlovs.cdc.springcloudcontractdemo.consumer

import io.iljapavlovs.cdc.springcloudcontractdemo.consumer.messaging.EventConsumer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.StubTrigger
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties

// resource - https://novotnyr.github.io/scrolls/enforcing-spring-cloud-contracts-over-amqp/
/**With the @AutoConfigureStubRunner annotation we tell the Stub Runner to load the Maven artifact with
 *   * the groupId io.iljapavlovs.cdc.spring-cloud-contract-demo
 *   * the artifactId provider,
 *   * of the newest version (+) and
 * The stubsMode is set to LOCAL meaning that the artifact should be resolved against the local Maven repository on our machine for now.
 * And since we have published the stub to our local Maven repository, it should resolve just fine.
 */
@SpringBootTest(properties = ["stubrunner.amqp.enabled=true"])
@AutoConfigureStubRunner(
    ids = ["io.iljapavlovs.cdc.spring-cloud-contract-demo:provider:+"],
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class AmqpContractTest {
    /**
     * Now, we need to emulate the following scenario:
     * 1. a message will be sent to the RabbitMQ broker,
     * 2. then routed to a queue,
     * 3. and finally processed by the Consumer.
     * We can autowire an StubTrigger that is able to emulate other-party message interactions.
     */
    @Autowired
    private lateinit var stubTrigger: StubTrigger

    @Autowired
    private lateinit var eventConsumer: EventConsumer

    @Test
    fun shouldReceiveMessage() {
        //refer to the particular part of the scenario via `label` that was specified way back in the YAML
        stubTrigger.trigger("person-created");

        assertThat(eventConsumer.createdPersons).hasSize(1)
    }
}

/*
*
* Within this integration test, the StubRunner will
* 1. find the proper AMQP binding,
* 2. configure in-memory RabbitMQ mock and
* 3. create the necessary RabbitMQ client infrastructure behind the scenes.
* 4. Shortly, this will allow the EventConsumer to handle AMQP messages as if they were routed by the proper RabbitMQ broker.
 */