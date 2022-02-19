package com.krivochkov.util

import com.krivochkov.routes.TypeRequest

object TokenValidator {

    fun validate(typeRequest: TypeRequest, token: String?) = when(typeRequest) {
        TypeRequest.POST -> token == POST_TOKEN
        TypeRequest.PATCH -> token == PATCH_TOKEN
        TypeRequest.DELETE -> token == DELETE_TOKEN
        TypeRequest.VERIFY -> token == VERIFY_TOKEN
    }
}