package com.krivochkov.di

import com.krivochkov.data.TestRepository
import com.krivochkov.data.TestRepositoryImpl
import com.krivochkov.util.dbName
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {

    single {
        KMongo.createClient()
            .coroutine
            .getDatabase(dbName)
    }

    single<TestRepository> {
        TestRepositoryImpl(get())
    }
}