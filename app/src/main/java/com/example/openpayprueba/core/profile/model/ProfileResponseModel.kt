package com.example.openpayprueba.core.profile.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProfileResponseModel(
    val results: ArrayList<ProfileResults>,
)

@Keep
data class ProfileResults(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    @SerializedName("known_for_department") val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    @SerializedName("profile_path") val profilePath: String
)


