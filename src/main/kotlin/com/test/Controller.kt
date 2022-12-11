package com.test

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class Controller(private val repository: DocumentRepository) {

    @Get("/get-documents")
    fun getDocuments(): List<Document> {
        return repository.findAll().toList()
    }
}