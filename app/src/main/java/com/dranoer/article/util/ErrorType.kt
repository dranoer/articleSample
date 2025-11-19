package com.dranoer.article.util

/**
 * Error types used to differentiate:
 * - No internet / timeout / 5xx
 * - Invalid response
 * - Backend-provided errors
 */
sealed class ErrorType {
    object Network : ErrorType()

    object Invalid : ErrorType()

    data class Backend(
        val errorCode: String,
        val errorTitle: String,
        val errorMessage: String
    ) : ErrorType()
}