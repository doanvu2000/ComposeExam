package com.jin.composeexam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "provinces")
data class ProvinceEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id") val id: String = "",
    @ColumnInfo(name = "code") val code: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "name_with_type") val nameWithType: String = "",
    @ColumnInfo(name = "slug") val slug: String = "",
    @ColumnInfo(name = "type") val type: String = ""
)