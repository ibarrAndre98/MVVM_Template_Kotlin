package com.example.openpayprueba.core.profile.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProfileResponseModel(
    val page: Int,
    val results: ArrayList<ProfileResults>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

@Keep
data class ProfileResults(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    @SerializedName("known_for") val knownFor: ArrayList<ProfileKnownFor>,
    @SerializedName("known_for_department") val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    @SerializedName("profile_path") val profilePath: String
)

@Keep
data class ProfileKnownFor(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: ArrayList<Int>,
    val id: Int,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)


