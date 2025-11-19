package com.dranoer.article.util

/**
 * A sealed class that encapsulates the result of an operation,
 * which can be either a success with data or a failure with an error type.
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val type: ErrorType) : Result<Nothing>()
}