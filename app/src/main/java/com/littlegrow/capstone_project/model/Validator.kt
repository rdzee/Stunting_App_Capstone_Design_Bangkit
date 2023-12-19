package com.littlegrow.capstone_project.model

object Validator {

    fun validateName(name: String): ValidationResult {
        return ValidationResult(name.isNotEmpty())
    }

    fun validateWeight(weight: String): ValidationResult {
        val isDouble = weight.toDoubleOrNull()
        return ValidationResult(weight.isNotEmpty() && isDouble != null)
    }

    fun validateHeight(height: String): ValidationResult {
        val isDouble = height.toDoubleOrNull()
        return ValidationResult(height.isNotEmpty() && isDouble != null)
    }

    fun validateBirthDate(birthDate: Long): ValidationResult {
        return ValidationResult(birthDate != 0L)
    }

    fun validateDiseaseHistory(diseaseHistory: String): ValidationResult {
        return ValidationResult(diseaseHistory.isNotEmpty())
    }

    fun validateBirthDistance(birthDistance: String): ValidationResult {
        val isInt = birthDistance.toIntOrNull()
        return ValidationResult(birthDistance.isNotEmpty() && isInt != null)
    }

    fun validateLingkarLengan(lingkarLengan: String): ValidationResult {
        val isDouble = lingkarLengan.toDoubleOrNull()
        return ValidationResult(lingkarLengan.isNotEmpty() && isDouble != null)
    }

    fun validateLingkarKepala(lingkarKepala: String): ValidationResult {
        val isDouble = lingkarKepala.toDoubleOrNull()
        return ValidationResult(lingkarKepala.isNotEmpty() && isDouble != null)
    }

    fun validateGender(gender: String): ValidationResult {
        return ValidationResult(gender.isNotEmpty())
    }
}

data class ValidationResult(
    val status: Boolean = false,
)