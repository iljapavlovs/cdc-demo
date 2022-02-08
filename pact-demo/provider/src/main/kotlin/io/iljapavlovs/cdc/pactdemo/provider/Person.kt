package io.iljapavlovs.cdc.pactdemo.provider

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull

data class PersonRequestDto(
    val name: String, val ssn: Int
)

data class PersonResponseDto(
    val id: UUID, val name: String, val ssn: Int
)

/**
 * Needed to add Kotlin JPA plugin
 */
@Entity
data class PersonEntity(
    @Id val id: UUID,

    @Column @NotNull var name: String,

    @Column @NotNull var ssn: Int
) {

    fun updateFrom(person: PersonEntity) {
        name = person.name
        ssn = person.ssn
    }
}


fun PersonRequestDto.toEntity(id: UUID): PersonEntity = PersonEntity(
    id = id, name = name, ssn = ssn
)

fun PersonEntity.toDto(): PersonResponseDto = PersonResponseDto(
    id = id, name = name, ssn = ssn
)