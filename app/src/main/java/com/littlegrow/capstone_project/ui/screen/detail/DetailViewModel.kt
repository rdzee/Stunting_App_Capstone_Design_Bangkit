package com.littlegrow.capstone_project.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littlegrow.capstone_project.data.DataRepository
import com.littlegrow.capstone_project.model.DeleteUpdateResponse
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.model.UpdateBodyData
import com.littlegrow.capstone_project.ui.screen.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DataRepository
): ViewModel() {
    private val _profileDetail = MutableStateFlow<Result<DetailDataResponse>>(Result.Loading)
    val profileDetail: StateFlow<Result<DetailDataResponse>>
        get() = _profileDetail

    private val _updateMessage = MutableStateFlow<Result<DeleteUpdateResponse>>(Result.Loading)
    val updateMessage: StateFlow<Result<DeleteUpdateResponse>>
        get() = _updateMessage

    private val _deleteMessage = MutableStateFlow<Result<DeleteUpdateResponse>>(Result.Loading)
    val deleteMessage: StateFlow<Result<DeleteUpdateResponse>>
        get() = _deleteMessage

    fun getProfileData(profileId: String) {
        viewModelScope.launch {
            repository.getDetailData(profileId)
                .collect {
                    _profileDetail.value = it
                }
        }
    }

    fun updateProfile(profileId: String, updateData: UpdateBodyData) {
        viewModelScope.launch {
            repository.updateProfile(
                profileId,
                updateData
            )
                .collect {
                    _updateMessage.value = it
                }
        }
    }

    fun deleteProfile(profileId: String) {
        viewModelScope.launch {
            repository.deleteProfile(profileId)
                .collect {
                    _deleteMessage.value = it
                }
        }
    }
}