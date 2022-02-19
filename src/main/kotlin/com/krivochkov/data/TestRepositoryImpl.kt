package com.krivochkov.data

import com.krivochkov.data.models.ShortTestInfo
import com.krivochkov.data.models.Test
import com.krivochkov.util.TEST_COLLECTION
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.setValue

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

    override suspend fun deleteTest(id: String) {
        tests.deleteOne(Test::id eq id)
    }

    override suspend fun updateVerifyStatus(id: String, status: Boolean) {
        tests.updateOne(Test::id eq id, setValue(Test::isVerified, status))
    }

    override suspend fun updateLinkOfTest(id: String, link: String) {
        tests.updateOne(Test::id eq id, setValue(Test::link, link))
    }

    override suspend fun getAllShortInfoTests(): List<ShortTestInfo> {
        return tests.find(Test::isVerified eq true).toList().map { it.toShortTestInfo() }
    }
}