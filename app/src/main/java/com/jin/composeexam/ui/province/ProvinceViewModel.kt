package com.jin.composeexam.ui.province

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jin.composeexam.data.database.ProvinceRepository
import com.jin.composeexam.data.model.DistrictEntity
import com.jin.composeexam.data.model.ProvinceEntity
import com.jin.composeexam.data.model.WardEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProvinceUiState(
    val provinces: List<ProvinceEntity> = emptyList(),
    val districts: List<DistrictEntity> = emptyList(),
    val wards: List<WardEntity> = emptyList()
)

@HiltViewModel
class ProvinceViewModel @Inject constructor(
    private val provinceRepository: ProvinceRepository
) : ViewModel() {
    companion object {
        const val TAG = "JinLog"
    }

    private val _uiState = MutableStateFlow(ProvinceUiState())
    val uiState: StateFlow<ProvinceUiState> = _uiState.asStateFlow()

    fun getProvinces() {
        viewModelScope.launch {
            Log.d(TAG, "getProvinces: start")
            val allProvince = provinceRepository.getAllProvinces()
            _uiState.value = _uiState.value.copy(provinces = allProvince)
        }
    }

    fun getDistrictsByProvinceId(provinceId: Int) {
        viewModelScope.launch {
            Log.d(TAG, "get districts: start")
            val districts = provinceRepository.getDistrictByProvinceId(provinceId)
            _uiState.value = _uiState.value.copy(districts = districts)
        }
    }

    fun getWardsByDistrictId(districtId: Int) {
        viewModelScope.launch {
            Log.d(TAG, "get wards: start")
            val wards = provinceRepository.getWardByDistrictId(districtId)
            _uiState.value = _uiState.value.copy(wards = wards)
        }
    }

}