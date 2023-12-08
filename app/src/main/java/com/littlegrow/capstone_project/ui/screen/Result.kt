package com.littlegrow.capstone_project.ui.screen

sealed class Result<out R> private constructor(){
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val error: String): Result<Nothing>()
    data object Loading: Result<Nothing>()
}