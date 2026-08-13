package com.example.todoapp.util

// sealed tipos controlados y conocidos no existe otro caso que no sea el descrito
sealed class Async<out T>{ // out T variance/covariance de kotlin
    // Async<List<Task>> Un estado asíncrono cuyo resultado, si tiene éxito, será una List<Task>

    // object implica una única instancia de Loading --> no es Loading() sino Async.Loading
    // object porque no se necesita guardar nada adicional
    // Nothing -> tipo Especial de kotlin que representa aquí no habrá valor
    object Loading : Async<Nothing>()

    // Aquí se recibe información adicional
    // Int porque se pasará un ID de recurso de Android
    data class Error(val errorMessage: Int) : Async<Nothing>()

    data class Success<out T>(val data: T) : Async<T>()
}