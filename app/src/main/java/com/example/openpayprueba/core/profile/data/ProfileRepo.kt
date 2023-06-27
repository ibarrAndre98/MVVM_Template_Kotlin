package com.example.openpayprueba.core.profile.data

import com.example.openpayprueba.core.core.network.Result
import com.example.openpayprueba.core.profile.model.ProfileResponseModel
import javax.inject.Inject

class ProfileRepo @Inject constructor(val profileDataSource: ProfileDataSource) {
    open suspend fun getPopularPeople(): Result<ProfileResponseModel> {
        return profileDataSource.getPopularPerson()
    }
}