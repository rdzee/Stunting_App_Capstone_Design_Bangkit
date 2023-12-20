package com.littlegrow.capstone_project.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.littlegrow.capstone_project.data.DataRepository
import com.littlegrow.capstone_project.ui.screen.choose.ChooseViewModel
import com.littlegrow.capstone_project.ui.screen.detail.DetailViewModel
import com.littlegrow.capstone_project.ui.screen.home.HomeViewModel
import com.littlegrow.capstone_project.ui.screen.input.InputDataViewModel
import com.littlegrow.capstone_project.ui.screen.recommendation.RecommendationViewModel

class ViewModelFactory(
    private val repository: DataRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(InputDataViewModel::class.java)) {
            return InputDataViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ChooseViewModel::class.java)) {
            return ChooseViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(RecommendationViewModel::class.java)) {
            return RecommendationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}