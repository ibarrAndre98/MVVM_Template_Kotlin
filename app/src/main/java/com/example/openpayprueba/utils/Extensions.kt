package com.example.openpayprueba.utils

import androidx.lifecycle.ViewModel
import com.example.openpayprueba.core.core.database.entities.PopularPeopleEntity
import com.example.openpayprueba.core.profile.model.ProfileResults

internal fun<E: Exception, T> ViewModel.emitUiState(showProgress: Boolean = false,
    showServerError: Event<E>? = null,
    showClientError: Event<E>? = null,
    showSuccess: Event<T>? = null
): UIModel<E, T> {
    return UIModel(showProgress, showServerError, showClientError, showSuccess)
}

fun ProfileResults.toDatabase() =
    PopularPeopleEntity(
        id = id,
        adult = adult,
        gender = gender,
        knownForDepartment = knownForDepartment,
        name = name,
        popularity = popularity,
        profilePath = profilePath
    )

fun PopularPeopleEntity.toDomain() =
    ProfileResults(
        id = id,
        adult = adult,
        gender = gender,
        knownForDepartment = knownForDepartment,
        name = name,
        popularity = popularity,
        profilePath = profilePath
    )