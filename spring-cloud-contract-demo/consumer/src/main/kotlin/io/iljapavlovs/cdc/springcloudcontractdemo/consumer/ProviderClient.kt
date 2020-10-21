package io.iljapavlovs.cdc.springcloudcontractdemo.consumer

import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.getForObject

@Component
class ProviderClient(
    private val props: ConsumerProperties,
    private val restTemplate: RestTemplate
) {
    private val log = LoggerFactory.getLogger(javaClass)

    public fun createPerson(personRequestDto: PersonRequestDto): PersonResponseDto? {
        return restTemplate.exchange<PersonResponseDto>(
            "${props.uri}/persons",
            HttpMethod.POST,
            HttpEntity(personRequestDto)
        ).body
            ?.also {
                log.info("Created new Person: $it")
            } ?: throw IllegalArgumentException("Received empty response")
    }

    public fun updatePerson(id: String, personRequestDto: PersonRequestDto): PersonResponseDto? {
        return restTemplate.exchange<PersonResponseDto>(
            "${props.uri}/persons/$id",
            HttpMethod.PUT,
            HttpEntity(personRequestDto)
        ).body
            ?.also {
                log.info("Updated Person: $it")
            } ?: throw IllegalArgumentException("Received empty response")
    }

    public fun getPerson(id: String): PersonResponseDto {
        return restTemplate.getForObject<PersonResponseDto>("${props.uri}/persons/$id")
            .also {
                log.info("Got Person: $it")
            }
    }
}
