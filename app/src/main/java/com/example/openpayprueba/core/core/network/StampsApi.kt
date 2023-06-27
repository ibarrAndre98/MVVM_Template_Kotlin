package com.example.openpayprueba.core.core.network

import com.example.openpayprueba.core.profile.model.ProfileResponseModel
import retrofit2.Response
import retrofit2.http.GET

@JvmSuppressWildcards
interface StampsApi {
    @GET("3/person/popular?")
    suspend fun getPopularPerson(): Response<ProfileResponseModel>
}