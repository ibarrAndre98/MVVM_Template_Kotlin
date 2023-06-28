package com.example.openpayprueba.core.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_people_table")
data class PopularPeopleEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "gender") val gender: Int,
    @ColumnInfo(name = "known_for_department") val knownForDepartment: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "profile_path") val profilePath: String
)