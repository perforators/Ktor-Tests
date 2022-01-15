package com.krivochkov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val range: String,
    val result: String
)