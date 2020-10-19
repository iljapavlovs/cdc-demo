package io.iljapavlovs.cdc.pactdemo.provider

import org.springframework.data.repository.CrudRepository


interface PersonRepository : CrudRepository<PersonEntity?, String?>