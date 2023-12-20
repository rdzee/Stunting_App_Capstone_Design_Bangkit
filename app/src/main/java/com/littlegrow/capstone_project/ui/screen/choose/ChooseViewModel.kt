package com.littlegrow.capstone_project.ui.screen.choose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.littlegrow.capstone_project.data.DataRepository
import com.littlegrow.capstone_project.model.DetailDataResponse
import com.littlegrow.capstone_project.ui.screen.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChooseViewModel(
    private val repository: DataRepository
) : ViewModel() {
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    private val _profileList = MutableStateFlow<Result<List<DetailDataResponse>>>(Result.Loading)
    val profileList: StateFlow<Result<List<DetailDataResponse>>>
        get() = _profileList

    fun getAllProfiles() {
        viewModelScope.launch {
            if (currentUserId != null) {
                repository.getListData(currentUserId)
                    .collect {
                        _profileList.value = it
                    }
            }
        }
    }
}