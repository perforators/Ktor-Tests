package com.krivochkov.data.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Test(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String,
    val questions: List<Question>,
    val results: List<Result>
)