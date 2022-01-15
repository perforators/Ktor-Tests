package com.krivochkov.plugins

import com.krivochkov.di.mainModule
import io.ktor.application.*
import org.koin.ktor.ext.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(mainModule)
    }
}