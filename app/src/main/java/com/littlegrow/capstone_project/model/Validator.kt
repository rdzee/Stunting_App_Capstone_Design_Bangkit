package com.littlegrow.capstone_project.model

object Validator {

    fun validateName(name: String): ValidationResult {
        return ValidationResult(name.isNotEmpty())
    }

    fun validateWeight(weight: String): ValidationResult {
        return ValidationResult(weight.isNotEmpty())
    }

    fun validateHeight(height: String): ValidationResult {
        return ValidationResult(height.isNotEmpty())
    }

    fun validateBirthDate(birthDate: Long): ValidationResult {
        return ValidationResult(birthDate != 0L)
    }

    fun validateDiseaseHistory(diseaseHistory: String): ValidationResult {
        return ValidationResult(diseaseHistory.isNotEmpty())
    }

    fun validateBirthDistance(birthDistance: String): ValidationResult {
        return ValidationResult(birthDistance.isNotEmpty() || birthDistance.isEmpty())
    }
}

data class ValidationResult(
    val status: Boolean = false,
)