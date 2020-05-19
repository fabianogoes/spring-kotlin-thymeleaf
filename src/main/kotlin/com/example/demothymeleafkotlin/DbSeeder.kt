package com.example.demothymeleafkotlin

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DbSeeder(private val repository: AuthorRepository): CommandLineRunner {
    override fun run(vararg args: String?) {
        listOf(
                Author(name = "Fabiano Goes", email = "fabiano@gmail.com"),
                Author(name = "James Gosling", email = "gosling@java.com"),
                Author(name = "Rod Johnson", email = "johnson@spring.com"),
                Author(name = "Jet Brains", email = "jet@brains.com"),
                Author(name = "Larry Page", email = "page@google.com"),
                Author(name = "Sergey Brin", email = "brin@google.com")
        ).let { repository.saveAll(it) }.forEach(::println)
    }
}