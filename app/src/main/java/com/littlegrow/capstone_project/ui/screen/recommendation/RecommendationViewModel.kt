package com.littlegrow.capstone_project.ui.screen.recommendation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littlegrow.capstone_project.data.DataRepository
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.model.FoodPredictData
import com.littlegrow.capstone_project.model.PredictionsItem
import com.littlegrow.capstone_project.ui.screen.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecommendationViewModel(
    private val repository: DataRepository
): ViewModel() {
    private val _profileDetail = MutableStateFlow<Result<DetailDataResponse>>(Result.Loading)
    val profileDetail: StateFlow<Result<DetailDataResponse>>
        get() = _profileDetail

    fun getProfileData(profileId: String) {
        viewModelScope.launch {
            repository.getDetailData(profileId)
                .collect {
                    _profileDetail.value = it
                }
        }
    }

    private val _foodList = MutableStateFlow<Result<List<PredictionsItem>>>(Result.Loading)
    val foodList: StateFlow<Result<List<PredictionsItem>>>
        get() = _foodList

    fun getFoodList(foodPredictData: FoodPredictData) {
        viewModelScope.launch {
            repository.predictFood(foodPredictData)
                .collect {
                    _foodList.value = it
                }
        }
    }
}