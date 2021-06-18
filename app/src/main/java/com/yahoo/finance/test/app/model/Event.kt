package com.yahoo.finance.test.app.model

sealed class Event<out T>(val data: T? = null, val error: Throwable? = null) {

    class Success<T>(data: T) : Event<T>(data)

    class Failure<T>(error: Throwable) : Event<T>(null, error)

    class Loading<T>(val loadingMessage: String? = null) : Event<T>(null, null)
}