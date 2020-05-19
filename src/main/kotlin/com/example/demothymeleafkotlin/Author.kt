package com.example.demothymeleafkotlin

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
data class Author(
        @Id @GeneratedValue
        var id: Long = 0,
        @get:Size(min=5, message = "{author.name.required}")
        var name: String = "",
        var email: String = ""
) {

        var test: String = ""
}