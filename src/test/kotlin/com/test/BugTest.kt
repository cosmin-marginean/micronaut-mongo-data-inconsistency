package com.test

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

@MicronautTest
class BugTest {

    @Inject
    lateinit var repository: DocumentRepository

    @Inject
    @field:Client("http://localhost:8080")
    lateinit var httpClient: HttpClient

    @Test
    fun `basic test`() {
        repository.save(Document("john", "smith"))
        repository.save(Document("jane", "doe"))
        assertEquals(2, repository.findAll().toList().size)
    }

    @Test
    fun `failing test`() {
        repository.save(Document("john", "smith"))
        repository.save(Document("jane", "doe"))
        assertEquals(2, repository.findAll().toList().size)
        val request = HttpRequest.GET<List<Document>>("/get-documents")
        val response = httpClient.toBlocking().exchange(request, List::class.java)
        assertEquals(2, response.body().size)
    }

    companion object {
        private val log = LoggerFactory.getLogger(Test::class.java)
    }
}