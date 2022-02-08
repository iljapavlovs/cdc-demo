package io.iljapavlovs.cdc.pactdemo.consumer

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslJsonBody
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import au.com.dius.pact.core.model.annotations.PactFolder
import org.apache.http.HttpResponse
import org.apache.http.client.fluent.Request
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


/**
 * Resources
 * https://reflectoring.io/consumer-driven-contract-feign-pact/
 * https://docs.pact.io/implementation_guides/jvm/consumer/junit5
 * https://github.com/thombergs/code-examples/tree/master/pact/pact-feign-consumer
 * https://github.com/vinogradoff/pact101/blob/master/consumer/src/test/java/PactConsumerWeatherTest.java
 * https://github.com/steam0/pact-consumer/blob/master/src/test/java/com/example/pact/consumer/ProviderPactTests.java
 * https://github.com/steam0/pact-guide
 * https://medium.com/@liran.tal/a-comprehensive-guide-to-contract-testing-apis-in-a-service-oriented-architecture-5695ccf9ac5a
 *
 *
 *
 *
 * NICE - https://github.com/christian-draeger/pact-example#define
 *
 *
 * Workshop - https://github.com/pact-foundation/pact-workshop-jvm-spring/tree/main
 */


@SpringBootTest
/**
 * With the PactConsumerTestExt together with the @PactTestFor annotation,
 * we tell pact to start a mock API provider on localhost:8888
 * This mock provider will return responses according to all pact fragments from the @Pact methods within the test class
 */
// 1. Add the Pact consumer test extension to the test class.
@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "userservice", port = "8888")
@PactFolder("build/pacts")
class RestConsumerContractTest {

    @Autowired
    private lateinit var providerClient: ProviderClient

    /**
     * This method defines a single interaction between a consumer and a provider, called a “fragment” of a pact.
     * A test class can contain multiple such fragments which together make up a complete pact
     */

    /**
     * The @Pact annotation tells Pact that we want to define a pact fragment
     * It contains the names of the consumer and the provider to uniquely identify the contract partners.
     */
    //2. create a method annotated with @Pact that returns the interactions for the test
    @Pact(provider = "userservice", consumer = "userclient")
    public fun createPersonPact(builder: PactDslWithProvider): RequestResponsePact {

        return builder
            .given("provider accepts a new person")//STATE!!!
            .uponReceiving("a request to POST a person")
            .path("/persons")
            .method("POST")
//            .body("{\"name\":\"Ivan\",\"ssn\":\"111\"}", "application/json")
            .body(PactDslJsonBody()
                .stringType("name")
                .stringType("ssn"))
            .headers(mapOf("Content-Type" to "application/json")) // Content type is mandatory if there is a request body, otherwise will get 500 in response
            .willRespondWith()
            /**
             * .status(200)
             * .body("{\"responsetest\": true}")
             */

            .status(201)
            .matchHeader("Content-Type", "application/json")
            .body(
                /**
                 * https://docs.pact.io/implementation_guides/jvm/consumer/#building-json-bodies-with-pactdsljsonbody-dsl
                 */
                PactDslJsonBody()
                        //show example by changing to UUIDType and opening build/pacts - show how matcher changed
                    .stringType("id") // stringType - just verifies type of the JSON key and randomly generates a value if second argument is not provided
                    .stringType("name", "Ivan")
                    //If no example value is given, a random one will be generated. (e.g. zGRVCO9FWN8Gq9Pbm5w7)
                    .stringType("ssn", "111")

                /**
                 * the same as "{\"id\": 42}"
                 */

            )
            .toPact()
    }

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    /**
     * The @PactTestFor annotation defines which pact fragment we want to test
     * (the fragment property must be the name of a method annotated with @Pact within the test class).
     */

    //3. Link the mock server with the interactions for the test with @PactTestFor
    @PactTestFor(pactMethod = "createPersonPact")
    fun createPerson() {

        val personResponseDto = providerClient.createPerson(
            PersonRequestDto(
                name = "Ivan",
                ssn = "111"
            )
        )

        assertThat(personResponseDto?.id).isNotNull()
        assertThat(personResponseDto?.name).isNotNull()
        assertThat(personResponseDto?.ssn).isNotNull() // ssn is generate with random value by Pact

        /**
         * Once the test has passed, a pact file with the name <consumer name>-<provider name>.json will be created in the build/pacts folder.
         */
    }

    /**
     * Injecting the mock server into the test
     * You can get the mock server injected into the test method by adding a MockServer parameter to the test method.
     * This helps with getting the base URL of the mock server, especially when a random port is used.
     */
    @Test
    @Disabled
    fun runMock(mockServer: MockServer) {
        val httpResponse: HttpResponse =
            Request.Get(mockServer.getUrl() + "/person/111").execute().returnResponse()
        assertThat(httpResponse.statusLine.statusCode).isEqualTo(200)
    }


    @Test
    @Disabled
    @PactTestFor(pactMethod = "getPersonPact")
    fun getPerson() {
        val person = providerClient.getPerson("1")
        assertThat(person.ssn).isEqualTo("1")
    }
}