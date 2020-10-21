package io.iljapavlovs.cdc.springcloudcontractdemo.provider

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean


@SpringBootTest(classes = [ProviderApplication::class])
abstract class CdcRestBase {
    @Autowired
    private lateinit var personController: PersonController

    @MockBean
    private lateinit var personRepository: PersonRepository

    @BeforeEach
    fun setup() {
        RestAssuredMockMvc.standaloneSetup(personController);

        val person = PersonEntity(
            id = "1",
            name = "Ivan",
            ssn = "111"
        )
        whenever(personRepository.save(any<PersonEntity>())) doReturn (person)
    }
}





