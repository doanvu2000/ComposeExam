package com.jin.composeexam.data.model

sealed class ProvinceState(val id: Int) {
    object ChooseProvince : ProvinceState(0)
    object ChooseDistrict : ProvinceState(1)
    object ChooseWard : ProvinceState(2)
    object NextScreen : ProvinceState(3)
}