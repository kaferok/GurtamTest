package com.bijov1apps.domain.utils

sealed class Result<out T> {

    data class Success<T>(val value: T) : Result<T>()

    data class Failure<T>(val error: String) : Result<T>()
}