package com.jin.composeexam.ui.info

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jin.composeexam.data.model.Screen
import com.jin.composeexam.ui.login.BaseButtonNext
import com.jin.composeexam.util.Constants
import com.jin.composeexam.util.showToast

private const val DISPLAY_NAME_MAX_LENGTH = 20

@Composable
fun InfoScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    var displayName by remember { mutableStateOf("") }
    val gender = listOf("Male", "Female")
    var genderSelect by remember { mutableStateOf("Male") }

    BackHandler(
        enabled = false
    ) {}
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.padding(16.dp),
            value = displayName,
            onValueChange = {
                if (it.length <= DISPLAY_NAME_MAX_LENGTH) {
                    displayName = it
                }
            },
            label = {
                Text(text = "Display Name")
            },
            supportingText = {
                Text("${displayName.length}/$DISPLAY_NAME_MAX_LENGTH")
            })

        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            gender.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(selected = it == genderSelect, onClick = { genderSelect = it })
                    Text(text = it)
                }
            }
        }

        BaseButtonNext(onClick = {
            if (displayName.isEmpty()) {
                context.showToast("Display Name must not empty!")
                return@BaseButtonNext
            }
            Constants.displayName = displayName
            Constants.gender = genderSelect

            navHostController.navigate(Screen.Province.route)
        }, text = "Continue")
    }
}