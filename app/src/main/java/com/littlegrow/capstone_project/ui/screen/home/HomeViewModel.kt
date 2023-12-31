package com.littlegrow.capstone_project.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.littlegrow.capstone_project.data.DataRepository
import com.littlegrow.capstone_project.data.local.database.Information
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.ui.screen.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: DataRepository
): ViewModel() {
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    private val _informationList = MutableStateFlow<Result<List<Information>>>(Result.Loading)
    val informationList: StateFlow<Result<List<Information>>>
        get() = _informationList

    private val _profileList = MutableStateFlow<Result<List<DetailDataResponse>>>(Result.Loading)
    val profileList: StateFlow<Result<List<DetailDataResponse>>>
        get() = _profileList

    fun getAllInfo() {
        viewModelScope.launch {
            repository.getAllInformation().asFlow()
                .collect {
                    _informationList.value = it
                }
        }
    }

    fun getAllProfiles() {
        viewModelScope.launch {
            if (currentUserId != null) {
                repository.getListData(currentUserId)
                    .collect{
                        _profileList.value = it
                    }
            }
        }
    }
}