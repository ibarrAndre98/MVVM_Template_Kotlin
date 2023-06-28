package com.example.openpayprueba.ui.Profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.openpayprueba.core.core.network.Result
import com.example.openpayprueba.core.core.network.error.ErrorType
import com.example.openpayprueba.core.core.network.error.StampsNetworkException
import com.example.openpayprueba.core.profile.domain.ProfileUseCase
import com.example.openpayprueba.core.profile.model.ProfileResults
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

    private val _getPopularPeopleState = MutableLiveData<UIModel<StampsNetworkException, ProfileResults>>()
    val getPopularPeopleState: LiveData<UIModel<StampsNetworkException, ProfileResults>>
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

        when(val popularDataResponse = profileUseCase.getPopularPeopleFromApi()) {
            is Result.Success -> {
                profileUseCase.insertPopularPeopleDb(popularDataResponse.data.results[0])
                _getPopularPeopleState.value = emitUiState(showSuccess = Event(popularDataResponse.data.results[0]))
            }

            is Result.Error -> {
                val exception = popularDataResponse.exception
                when(exception.getType()){
                    ErrorType.CONNECTION -> {
                        _getPopularPeopleState.value = emitUiState(showSuccess = Event(profileUseCase.getPopularPeopleFromDb()))
                    }
                    else -> {
                        _getPopularPeopleState.value = emitUiState(showServerError = Event(exception))
                    }
                }
            }
        }
    }
}