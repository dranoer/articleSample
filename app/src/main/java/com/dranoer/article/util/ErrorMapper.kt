package com.dranoer.article.util

import retrofit2.HttpException
import java.io.IOException

internal fun Throwable.toErrorType(): ErrorType = when (this) {
    is IOException -> ErrorType.Network
    is HttpException -> {
        ErrorType.Backend(
            errorCode = code().toString(),
            errorTitle = "Server error",
            errorMessage = "Please try again later."
        )
    }

    else -> ErrorType.Invalid
}

internal fun ErrorType.toUserMessage(): String = when (this) {
    ErrorType.Network -> "No internet or server unavailable."
    ErrorType.Invalid -> "Something went wrong."
    is ErrorType.Backend -> "$errorTitle: $errorMessage"
}