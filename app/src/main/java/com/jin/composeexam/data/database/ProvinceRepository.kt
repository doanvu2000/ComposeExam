package com.jin.composeexam.data.database

import com.jin.composeexam.repositories.ProvinceDao
import javax.inject.Inject

class ProvinceRepository @Inject constructor(
    private val provinceDao: ProvinceDao
) {

    suspend fun getAllProvinces() = provinceDao.getAllProvinces()
    suspend fun getProvinceById(id: String) = provinceDao.getProvinceById(id)

    suspend fun getAllDistricts() = provinceDao.getAllDistricts()
    suspend fun getDistrictByProvinceId(provinceId: Int) =
        provinceDao.getDistrictByProvinceId(provinceId)

    suspend fun getAllWards() = provinceDao.getAllWards()
    suspend fun getWardByDistrictId(districtId: Int) =
        provinceDao.getWardByDistrictId(districtId)

}