package com.jin.composeexam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "districts")
data class DistrictEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: String = "",
    @ColumnInfo("code")
    val code: Int = 0,
    @ColumnInfo("name")
    val name: String = "",
    @ColumnInfo(name = "name_with_type")
    val nameWithType: String = "",
    @ColumnInfo(name = "parent_code")
    val parentCode: Int = 0,
    @ColumnInfo("path")
    val path: String = "",
    @ColumnInfo(name = "path_with_type")
    val pathWithType: String = "",
    @ColumnInfo("slug")
    val slug: String = "",
    @ColumnInfo("type")
    val type: String = ""
)