package com.example.openpayprueba.core.profile.data

import com.example.openpayprueba.core.core.database.entities.PopularPeopleEntity
import com.example.openpayprueba.core.core.network.Result
import com.example.openpayprueba.core.profile.model.ProfileResponseModel
import javax.inject.Inject

class ProfileRepo @Inject constructor(val profileDataSource: ProfileDataSource) {
    open suspend fun getPopularPeople(): Result<ProfileResponseModel> {
        return profileDataSource.getPopularPeople()
    }

    open suspend fun getPopularPeopleFromDb(): PopularPeopleEntity {
        return profileDataSource.getPopularPeopleFromDb()
    }
    open suspend fun insertPopularPeopleDb(popularPeople: PopularPeopleEntity){
        profileDataSource.insertPopularPeople(popularPeople)
    }
}