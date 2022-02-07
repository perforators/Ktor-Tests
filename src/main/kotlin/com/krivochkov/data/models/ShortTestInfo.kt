package com.krivochkov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ShortTestInfo(
    val id: String,
    val name: String,
    val link: String
)