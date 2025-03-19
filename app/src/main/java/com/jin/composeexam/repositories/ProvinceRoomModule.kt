package com.jin.composeexam.repositories

import android.content.Context
import androidx.room.Room
import com.jin.composeexam.data.database.ProvinceDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvinceRoomModule {
    @Singleton
    @Provides
    fun provideProvinceDatabase(@ApplicationContext context: Context): ProvinceDatabase {
        return Room.databaseBuilder(context, ProvinceDatabase::class.java, "vn_province.db")
            .createFromAsset("vietnam_province.db")
            .allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideProvinceDao(provinceDatabase: ProvinceDatabase) = provinceDatabase.dao()
}