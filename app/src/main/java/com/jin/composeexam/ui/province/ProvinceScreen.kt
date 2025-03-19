package com.jin.composeexam.ui.province

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jin.composeexam.data.enumz.ProvinceType
import com.jin.composeexam.data.enumz.ProvinceType.District
import com.jin.composeexam.data.enumz.ProvinceType.Province
import com.jin.composeexam.data.enumz.ProvinceType.Ward
import com.jin.composeexam.data.model.ProvinceState
import com.jin.composeexam.data.model.Screen
import com.jin.composeexam.ui.login.BaseButtonNext
import com.jin.composeexam.ui.theme.backgroundColor4
import com.jin.composeexam.ui.theme.primaryColor4
import com.jin.composeexam.util.Constants
import com.jin.composeexam.util.getColorByCondition
import com.jin.composeexam.util.showToast

@Composable
fun ProvinceScreen(
    navHostController: NavHostController, viewModel: ProvinceViewModel
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    var provinceState by remember { mutableIntStateOf(ProvinceState.ChooseProvince.id) }

    var provinceName by remember { mutableStateOf(Province.type) }
    var districtName by remember { mutableStateOf(District.type) }
    var wardName by remember { mutableStateOf(Ward.type) }

    LaunchedEffect(Unit) {
        viewModel.getProvinces()
    }

    LaunchedEffect(provinceState) {
        when (ProvinceState.getProvinceState(provinceState)) {
            ProvinceState.ChooseDistrict, ProvinceState.ChooseProvince, ProvinceState.ChooseWard -> {
            }

            ProvinceState.NextScreen -> {
                //navigate to home
                Constants.province = provinceName
                Constants.district = districtName
                Constants.ward = wardName
                navHostController.navigate(Screen.Home.route)
            }
        }
    }

    BackHandler(enabled = false) { }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "State Screen",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(16.dp))

        Row(Modifier.horizontalScroll(rememberScrollState())) {
            Text(
                text = provinceName, color = getColorByCondition(
                    ProvinceType.getProvinceType(provinceName) == Province, Color.Black, Color.Blue
                ), modifier = Modifier.clickable(onClick = {
                    //todo: choose province
                    if (provinceState == ProvinceState.ChooseProvince.id) {
                        return@clickable
                    }
                    districtName = "District"
                    wardName = "Ward"
                    provinceState = ProvinceState.ChooseProvince.id
                })
            )

            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, null)

            Text(
                text = districtName,
                color = if (ProvinceType.getProvinceType(districtName) == District) {
                    Color.Black
                } else {
                    Color.Blue
                },
                modifier = Modifier.clickable(onClick = {
                    //todo: choose district
                    if (provinceState == ProvinceState.ChooseDistrict.id) {
                        return@clickable
                    }
                    if (provinceName == "Province") {//not select province
                        return@clickable
                    }
                    districtName = "District"
                    wardName = "Ward"
                    provinceState = ProvinceState.ChooseDistrict.id
                })
            )

            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, null)

            Text(
                text = wardName, color = if (ProvinceType.getProvinceType(wardName) == Ward) {
                    Color.Black
                } else {
                    Color.Blue
                }, modifier = Modifier.clickable(onClick = {
                    //todo: choose ward
                    if (provinceState == ProvinceState.ChooseWard.id) {
                        return@clickable
                    }
                    if (districtName == "District") {//not select district
                        return@clickable
                    }
                    wardName = "Ward"
                    provinceState = ProvinceState.ChooseWard.id
                })
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            when (ProvinceState.getProvinceState(provinceState)) {
                ProvinceState.ChooseProvince -> {
                    items(uiState.provinces.size) { index ->
                        val item = uiState.provinces[index]
                        ItemProvince(onClick = {
                            //update ui, next to choose district
                            provinceName = item.nameWithType
                            districtName = "District"
                            wardName = "Ward"
                            viewModel.getDistrictsByProvinceId(item.code)
                            provinceState = ProvinceState.ChooseDistrict.id
                        }, item.nameWithType)
                    }
                }

                ProvinceState.ChooseDistrict -> {
                    items(uiState.districts.size) { index ->
                        val item = uiState.districts[index]
                        ItemProvince(onClick = {
                            //update ui, next to choose district
                            districtName = item.nameWithType
                            wardName = "Ward"
                            viewModel.getWardsByDistrictId(item.code)
                            provinceState = ProvinceState.ChooseWard.id
                        }, item.nameWithType)
                    }
                }

                ProvinceState.ChooseWard -> {
                    items(uiState.wards.size) { index ->
                        val item = uiState.wards[index]
                        ItemProvince(onClick = {
                            //update ui, next to choose district
                            wardName = item.nameWithType
                            Constants.result = item.pathWithType
                        }, item.nameWithType)
                    }
                }

                ProvinceState.NextScreen -> {}
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            BaseButtonNext(
                onClick = {
                    if (provinceName != "Province" && districtName != "District" && wardName != "Ward") {
                        provinceState = ProvinceState.NextScreen.id
                    } else {
                        context.showToast("Please choose province, district and ward.")
                    }
                }, text = "Continue"
            )
        }
    }
}

@Composable
fun ItemProvince(onClick: () -> Unit, text: String) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = {
            onClick()
        },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor4),
        shape = CardDefaults.elevatedShape
    ) {
        Text(
            text = text, color = primaryColor4, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}