package com.jin.composeexam.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jin.composeexam.data.model.DistrictEntity
import com.jin.composeexam.data.model.ProvinceEntity
import com.jin.composeexam.data.model.WardEntity
import com.jin.composeexam.repositories.ProvinceDao

@Database(
    entities = [ProvinceEntity::class, DistrictEntity::class, WardEntity::class],
    version = 1,
    exportSchema = true
)
abstract class ProvinceDatabase : RoomDatabase() {
    abstract fun dao(): ProvinceDao
}