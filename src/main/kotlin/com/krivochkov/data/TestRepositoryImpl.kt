package com.krivochkov.data

import com.krivochkov.data.models.ShortTestInfo
import com.krivochkov.data.models.Test
import com.krivochkov.util.TEST_COLLECTION
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class TestRepositoryImpl(
    database: CoroutineDatabase
): TestRepository {

    private val tests = database.getCollection<Test>(TEST_COLLECTION)

    override suspend fun getTest(id: String): Test? {
        return tests.findOne(Test::id eq id)
    }

    override suspend fun postTest(test: Test) {
        tests.insertOne(test)
    }

    override suspend fun getAllShortInfoTests(): List<ShortTestInfo> {
        return tests.find().toList().map { it.toShortTestInfo() }
    }
}