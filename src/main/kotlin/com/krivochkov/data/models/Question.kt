package com.krivochkov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val question: String,
    val isPicture: Boolean,
    val answers: List<Answer>,
)