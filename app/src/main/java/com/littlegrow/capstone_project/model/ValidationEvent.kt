package com.littlegrow.capstone_project.model

sealed class ValidationEvent {
    object Success: ValidationEvent()
}