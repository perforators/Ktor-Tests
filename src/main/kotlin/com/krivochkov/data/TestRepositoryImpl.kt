package com.krivochkov.data

import com.krivochkov.data.models.Test
import com.krivochkov.util.testCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq

class TestRepositoryImpl(
    database: CoroutineDatabase
): TestRepository {

    private val tests = database.getCollection<Test>(testCollection)

    override suspend fun getTest(id: String): Test? {
        return tests.findOne(Test::id eq id)
    }

    override suspend fun postTest(test: Test) {
        tests.insertOne(test)
    }
}