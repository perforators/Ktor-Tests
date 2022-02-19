package com.krivochkov.data

import com.krivochkov.data.models.ShortTestInfo
import com.krivochkov.data.models.Test

interface TestRepository {

    suspend fun getTest(id: String): Test?

    suspend fun postTest(test: Test)

    suspend fun deleteTest(id: String)

    suspend fun updateVerifyStatus(id: String, status: Boolean)

    suspend fun updateLinkOfTest(id: String, link: String)

    suspend fun getAllShortInfoTests(): List<ShortTestInfo>
}