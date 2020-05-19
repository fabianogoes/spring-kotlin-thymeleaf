package com.example.demothymeleafkotlin

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
@RequestMapping("/authors")
class AuthorController(private val repository: AuthorRepository) {

    companion object {
        const val TEMPLATE = "authors"
        const val REDIRECT = "redirect:/authors"
        const val FORM_OBJECT = "author"
        const val SUCCESS_MESSAGE = "success_message"
        const val TABLE_LIST = "authors"
        const val AUTHOR_SAVED = "Author saved Successfully"
        const val AUTHOR_DELETED = "Author deleted Successfully"
    }

    @GetMapping
    fun all(model: Model): String {
        println("all()...")
        val authors = repository.findAll()
        model. addAttribute(TABLE_LIST, authors)
        return TEMPLATE
    }

    @PostMapping
    fun save(@Valid author: Author, errors: Errors, redirectAttributes: RedirectAttributes): String {
        println("save()...")
        return when {
            errors.hasErrors() -> TEMPLATE
            else -> {
                repository.save(author).also(::println)
                redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, AUTHOR_SAVED)
                return REDIRECT
            }
        }
    }

    @GetMapping("/new")
    fun new(model: Model): String {
        println("new()...")
        model.addAttribute(FORM_OBJECT, Author())
        return TEMPLATE
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        println("edit($id)")
        val author = repository.findByIdOrNull(id)
        model.addAttribute(FORM_OBJECT, author)
        return TEMPLATE
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long, redirectAttributes: RedirectAttributes): String {
        println("delete($id)")
        val author = repository.deleteById(id)
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, AUTHOR_DELETED)
        return REDIRECT
    }

}