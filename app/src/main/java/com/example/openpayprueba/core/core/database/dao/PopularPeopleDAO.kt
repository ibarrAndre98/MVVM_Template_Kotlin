package com.example.openpayprueba.core.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openpayprueba.core.core.database.entities.PopularPeopleEntity

@Dao
interface PopularPeopleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: PopularPeopleEntity)

    @Query("SELECT * FROM popular_people_table limit 1")
    suspend fun getPopularPeople(): PopularPeopleEntity
}