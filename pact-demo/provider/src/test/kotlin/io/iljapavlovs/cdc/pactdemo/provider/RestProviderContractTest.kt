package io.iljapavlovs.cdc.pactdemo.provider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = ["server.port=8888"])
/**
 * The annotation @Provider("userservice") tells Pact that we’re testing the provider called “userservice”.
 * Pact will automatically filter the interactions from the loaded pact files so that only those interaction with this provider are being tested.
 */
@Provider("userservice")

/**
 * With @PactFolder we tell Pact where to look for pact files that serve as the base for our contract test.
 * Note that there are other options for loading pact files such as the @PactBroker annotation
 */
//@PactFolder("pacts")
@PactBroker(host = "localhost", port = "9292")
class RestProviderContractTest {
    @MockBean
    private lateinit var personRepository: PersonRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    /**
     * Since Pact creates a mock consumer for us that “replays” all requests from the pact files,
     * it needs to know where to send those requests. In the @BeforeEach annotated method,
     * we define the target for those requests by calling PactVerificationContext#setTarget().
     * This should target the Spring Boot application we started with @SpringBootTest so the ports must match.
     */
    @BeforeEach
    fun setupTestTarget(context: PactVerificationContext) {
        context.target = HttpTestTarget("localhost", 8888, "/")
//        these are set in build.gradle.kts
//        System.setProperty("pact.verifier.publishResults", "true");
//        System.setProperty("pact.provider.version", "buildVersion");
    }

    /**
     * there is an JUnit 5 Invocation Context Provider that you can use with the @TestTemplate annotation
     * This will generate a test for each interaction found for the pact files for the provider.
     * For each interaction from the pact file, context.verifyInteraction() will be called.
     * This will automatically call the correct @State method and
     * then fire the request defined in the interaction verify the result against the pact.
     */
    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }


    /**
     * Next, we create a method annotated with @State that puts our Spring Boot application
     * into a defined state that is suitable to respond to the mock consumer’s requests.
     * In our case, the pact file defines a single providerState named provider accepts a new person.
     * In this method, we set up our mock repository
     * so that it returns a suitable User object that fits the object expected in the contract
     */

    //PROVIDER STATE - SO IT SHOULD MATCH THE GIVEN IN CONSUMER
    @State("provider accepts a new person")
    fun toCreatePersonState() {

        val person = PersonEntity(
            id = UUID.randomUUID(),
            name = "Ivan",
            ssn = 111
        )
        whenever(personRepository.save(any<PersonEntity>())) doReturn (person)
    }
}