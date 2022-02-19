package com.krivochkov.routes

import com.krivochkov.data.TestRepository
import com.krivochkov.data.models.Test
import com.krivochkov.util.TokenValidator
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.testRouting(testRepository: TestRepository) {

    route("/api/v1/test") {
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

        get("/all/info") {
            val allTests = testRepository.getAllShortInfoTests()

            call.respond(
                message = allTests,
                status = HttpStatusCode.OK
            )
        }

        post {
            val token = call.parameters["token"]
            if (!TokenValidator.validate(TypeRequest.POST, token)) return@post call.respondText(
                text = "Incorrect token",
                status = HttpStatusCode.BadRequest
            )

            val test = call.receiveOrNull<Test>() ?: return@post call.respondText(
                text = "Incorrect test",
                status = HttpStatusCode.BadRequest
            )

            testRepository.postTest(test)

            call.respond(
                message = test.id,
                status = HttpStatusCode.Created
            )
        }

        patch("/link/{id}") {
            val token = call.parameters["token"]
            if (!TokenValidator.validate(TypeRequest.PATCH, token)) return@patch call.respondText(
                text = "Incorrect token",
                status = HttpStatusCode.BadRequest
            )

            val id = call.parameters["id"] ?: return@patch call.respondText(
                text = "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val link = call.receiveOrNull<String>() ?: return@patch call.respondText(
                text = "Incorrect link",
                status = HttpStatusCode.BadRequest
            )

            testRepository.updateLinkOfTest(id, link)

            call.respondText(
                text = "Test updated correctly",
                status = HttpStatusCode.OK
            )
        }

        patch("/verify/{id}") {
            val token = call.parameters["token"]
            if (!TokenValidator.validate(TypeRequest.VERIFY, token)) return@patch call.respondText(
                text = "Incorrect token",
                status = HttpStatusCode.BadRequest
            )

            val id = call.parameters["id"] ?: return@patch call.respondText(
                text = "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val status = call.receiveOrNull<Boolean>() ?: return@patch call.respondText(
                text = "Incorrect status",
                status = HttpStatusCode.BadRequest
            )

            testRepository.updateVerifyStatus(id, status)

            call.respondText(
                text = "Verify status updated",
                status = HttpStatusCode.OK
            )
        }

        delete("{id}") {
            val token = call.parameters["token"]
            if (!TokenValidator.validate(TypeRequest.DELETE, token)) return@delete call.respondText(
                text = "Incorrect token",
                status = HttpStatusCode.BadRequest
            )

            val id = call.parameters["id"] ?: return@delete call.respondText(
                text = "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            testRepository.deleteTest(id)

            call.respondText(
                text = "Test deleted correctly",
                status = HttpStatusCode.OK
            )
        }
    }
}