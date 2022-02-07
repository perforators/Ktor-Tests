package com.krivochkov.plugins

import com.krivochkov.data.TestRepository
import com.krivochkov.routes.testRouting
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val testRepository by inject<TestRepository>()

    install(Routing) {
        testRouting(testRepository)
    }
}
