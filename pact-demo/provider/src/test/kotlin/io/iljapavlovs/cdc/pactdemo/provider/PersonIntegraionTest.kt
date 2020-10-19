package io.iljapavlovs.cdc.pactdemo.provider

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PersonIntegraionTest {

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun createPerson() {

        val personRequestDto = PersonRequestDto(name = "Ivan", ssn = "111")

        mockMvc.perform(
            post("/persons")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(personRequestDto))
        )
            .andExpect(status().isCreated);

        val persons = personRepository.findAll()

        assertThat(persons).hasSize(1);
    }

    @Test
    fun updatePerson() {
    }

    @Test
    fun getPerson() {
    }
}