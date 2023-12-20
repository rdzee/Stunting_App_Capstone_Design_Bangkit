package com.littlegrow.capstone_project.model

sealed class ValidationEvent {
    data object Success: ValidationEvent()

    data object Error: ValidationEvent()

    data object Loading: ValidationEvent()
}