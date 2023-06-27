package com.example.openpayprueba.core.profile.domain

import com.example.openpayprueba.core.core.network.Result
import com.example.openpayprueba.core.profile.data.ProfileRepo
import com.example.openpayprueba.core.profile.model.ProfileResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileUseCase @Inject constructor(val profileRepo: ProfileRepo) {

     suspend fun invoke(): Result<ProfileResponseModel>{
        return when (val response = profileRepo.getPopularPeople()) {
            is Result.Success -> {
                Result.Success(response.data)
            }

            is Result.Error -> {
                Result.Error(response.exception)
            }
        }
    }
}