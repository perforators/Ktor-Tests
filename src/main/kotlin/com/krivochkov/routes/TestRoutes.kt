package com.krivochkov.routes

import com.krivochkov.data.TestRepository
import com.krivochkov.data.models.Test
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.testsRouting(testRepository: TestRepository) {

    route("/test") {
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                text = "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val test = testRepository.getTest(id) ?: return@get call.respondText(
                text = "No test with id $id",
                status = HttpStatusCode.NotFound
            )

            call.respond(
                message = test,
                status = HttpStatusCode.OK
            )
        }

        post {
            val test = call.receiveOrNull<Test>() ?: return@post call.respondText(
                text = "Incorrect test",
                status = HttpStatusCode.BadRequest
            )

            testRepository.postTest(test)

            call.respondText(
                text = "Test posted correctly",
                status = HttpStatusCode.Created
            )
        }
    }
}