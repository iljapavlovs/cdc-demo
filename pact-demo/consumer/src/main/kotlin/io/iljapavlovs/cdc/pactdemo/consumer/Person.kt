package io.iljapavlovs.cdc.pactdemo.consumer

data class PersonRequestDto(
     val name: String,
     val ssn: String
)

data class PersonResponseDto(
     val id: String,
     val name: String,
     val ssn: String
)