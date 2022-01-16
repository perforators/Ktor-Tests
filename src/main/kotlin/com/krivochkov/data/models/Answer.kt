package com.krivochkov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    val answer: String,
    val weight: Int
)