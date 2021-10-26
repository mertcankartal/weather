package com.example.weather

sealed class Result<T>(val data :  T?) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(errorMessage: String) : Result<T>(null)
}