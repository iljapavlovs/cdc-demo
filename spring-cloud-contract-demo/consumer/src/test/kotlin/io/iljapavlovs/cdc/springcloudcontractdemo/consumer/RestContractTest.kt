package io.iljapavlovs.cdc.springcloudcontractdemo.consumer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties

/**With the @AutoConfigureStubRunner annotation we tell the Stub Runner to load the Maven artifact with
 *   * the groupId io.iljapavlovs.cdc.spring-cloud-contract-demo
 *   * the artifactId provider,
 *   * of the newest version (+) and
 *   * with the stubs qualifier
 * from a Maven repository, extract the contract from it and pass it into the Stub Runner who then acts as the API provider on port 8888
 * The stubsMode is set to LOCAL meaning that the artifact should be resolved against the local Maven repository on our machine for now. And since we have published the stub to our local Maven repository, it should resolve just fine.
 */
@SpringBootTest
@AutoConfigureStubRunner(
    ids = ["io.iljapavlovs.cdc.spring-cloud-contract-demo:provider:+:stubs:8888"],
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class RestContractTest {
    @Autowired
    private lateinit var providerClient: ProviderClient

    @Test
    fun createUserCompliesToContract() {
        val person = providerClient.createPerson(
            PersonRequestDto(name = "Ivan", ssn = "111")
        )
        assertThat(person?.id).isEqualTo("1")
    }
}