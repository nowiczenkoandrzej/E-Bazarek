package com.nowiczenkoandrzej.e_bazarek.utils

sealed class DataState<T>(val data: T?, val message: String?) {
    class Success<T>(data: T?): DataState<T>(data, null)
    class Error<T>(message: String): DataState<T>(null, message)
    class Loading<T>: DataState<T>(null, null)
}