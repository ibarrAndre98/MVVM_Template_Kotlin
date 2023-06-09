package com.example.openpayprueba.core.profile.domain

import com.example.openpayprueba.core.profile.data.ProfileRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileUseCase @Inject constructor(val profileRepo: ProfileRepo) {

    suspend operator fun invoke(){

    }
}