package com.example.openpayprueba.core.core.di

import android.content.Context
import androidx.room.Room
import com.example.openpayprueba.core.core.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = "my_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MyDatabase::class.java , DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providePopularPeopleDao(db: MyDatabase) = db.getPopularPeopleDao()
}