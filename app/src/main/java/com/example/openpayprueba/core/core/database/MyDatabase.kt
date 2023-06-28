package com.example.openpayprueba.core.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.openpayprueba.core.core.database.dao.PopularPeopleDAO
import com.example.openpayprueba.core.core.database.entities.PopularPeopleEntity

@Database(entities = [PopularPeopleEntity::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun getPopularPeopleDao(): PopularPeopleDAO
}