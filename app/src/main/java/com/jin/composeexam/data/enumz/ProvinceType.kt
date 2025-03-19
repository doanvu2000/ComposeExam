package com.jin.composeexam.data.enumz

import com.jin.composeexam.data.enumz.ProvinceType.entries


enum class ProvinceType(val type: String) {
    Province("Province"),
    District("District"),
    Ward("Ward");

    companion object {
        fun getProvinceType(name: String) = entries.find { it.type == name } ?: Province
    }
}