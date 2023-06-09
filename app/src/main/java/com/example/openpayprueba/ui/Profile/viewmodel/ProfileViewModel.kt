package com.example.openpayprueba.ui.Profile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.openpayprueba.core.profile.domain.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
): ViewModel() {
}