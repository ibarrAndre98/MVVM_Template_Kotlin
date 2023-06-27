package com.example.openpayprueba.ui.Profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.openpayprueba.core.core.network.Result
import com.example.openpayprueba.core.core.network.error.ErrorType
import com.example.openpayprueba.core.core.network.error.StampsNetworkException
import com.example.openpayprueba.core.profile.domain.ProfileUseCase
import com.example.openpayprueba.core.profile.model.ProfileResponseModel
import com.example.openpayprueba.utils.Event
import com.example.openpayprueba.utils.UIModel
import com.example.openpayprueba.utils.emitUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
): ViewModel() {

    private val _getPopularPeopleState = MutableLiveData<UIModel<StampsNetworkException, ProfileResponseModel>>()
    val getPopularPeopleState: LiveData<UIModel<StampsNetworkException, ProfileResponseModel>>
        get() = _getPopularPeopleState

    private var currentPopularPeopleJob: Job? = null
    fun getPopularPeople(){
        if(currentPopularPeopleJob?.isActive == true){
            return
        }

        currentPopularPeopleJob = getPopularPeopleData()
    }

    private fun getPopularPeopleData() = GlobalScope.launch(Dispatchers.Main) {
        _getPopularPeopleState.value = emitUiState(showProgress = true)

        when(val popularDataResponse = profileUseCase.invoke()) {
            is Result.Success -> {
                _getPopularPeopleState.value = emitUiState(showSuccess = Event(popularDataResponse.data))
            }

            is Result.Error -> {
                val exception = popularDataResponse.exception
                when(exception.getType()){
                    ErrorType.GENERIC, ErrorType.CONNECTION -> {
                        _getPopularPeopleState.value = emitUiState(showClientError = Event(exception))
                    } else -> {
                        _getPopularPeopleState.value = emitUiState(showServerError = Event(exception))
                    }
                }
            }
        }
    }
}