package com.krivochkov.data

import com.krivochkov.data.models.Test

interface TestRepository {

    suspend fun getTest(id: String): Test?

    suspend fun postTest(test: Test)
}