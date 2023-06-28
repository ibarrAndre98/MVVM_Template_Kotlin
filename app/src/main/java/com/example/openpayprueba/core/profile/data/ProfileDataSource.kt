package com.example.openpayprueba.core.profile.data

import com.example.openpayprueba.core.core.database.dao.PopularPeopleDAO
import com.example.openpayprueba.core.core.database.entities.PopularPeopleEntity
import com.example.openpayprueba.core.core.network.Result
import com.example.openpayprueba.core.core.network.StampsApi
import com.example.openpayprueba.core.core.network.error.StampsErrorAdapter
import com.example.openpayprueba.core.core.network.error.StampsNetworkException
import com.example.openpayprueba.core.core.network.safeApiCall
import com.example.openpayprueba.core.profile.model.ProfileResponseModel
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    val stampsApi: StampsApi,
    private val popularPeopleDAO: PopularPeopleDAO
    ) {

    suspend fun getPopularPeople() = safeApiCall(
        call = { getPopularPeopleCall() },
        errorMessage = "Cannot get popular person"
    )
    suspend fun getPopularPeopleCall(): Result<ProfileResponseModel> {
        val response = stampsApi.getPopularPerson()

        if(response.isSuccessful) {
            val body = response.body()
            if(body != null){
                return Result.Success(body)
            }
        }
        return Result.Error(StampsNetworkException(StampsErrorAdapter().mapServerError(response.errorBody())))
    }

    suspend fun getPopularPeopleFromDb(): PopularPeopleEntity {
        return popularPeopleDAO.getPopularPeople()
    }
    suspend fun insertPopularPeople(popularPeople: PopularPeopleEntity) {
        popularPeopleDAO.insertPeople(popularPeople)
    }

}