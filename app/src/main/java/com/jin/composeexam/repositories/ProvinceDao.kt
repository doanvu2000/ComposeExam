package com.jin.composeexam.repositories

import androidx.room.Dao
import androidx.room.Query
import com.jin.composeexam.data.model.DistrictEntity
import com.jin.composeexam.data.model.ProvinceEntity
import com.jin.composeexam.data.model.WardEntity

@Dao
interface ProvinceDao {
    @Query("SELECT * FROM provinces")
    suspend fun getAllProvinces(): List<ProvinceEntity>

    @Query("SELECT * FROM provinces WHERE _id = :id")
    suspend fun getProvinceById(id: String): ProvinceEntity

    @Query("select * from districts")
    suspend fun getAllDistricts(): List<DistrictEntity>

    @Query("select * from districts where parent_code = :provinceId")
    suspend fun getDistrictByProvinceId(provinceId: Int): List<DistrictEntity>

    @Query("select * from wards")
    suspend fun getAllWards(): List<WardEntity>

    @Query("select * from wards where parent_code = :districtId")
    suspend fun getWardByDistrictId(districtId: Int): List<WardEntity>
}