package com.jin.composeexam.ui.login

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jin.composeexam.R
import com.jin.composeexam.data.model.Screen
import com.jin.composeexam.ui.BaseButtonNext
import com.jin.composeexam.ui.ColumnCenterItem
import com.jin.composeexam.util.clickOutsideListener
import com.jin.composeexam.util.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val USER_NAME_MAX_LENGTH = 20
private const val PASS_WORD_MAX_LENGTH = 15

sealed class StateScreen(val id: Int) {
    object Init : StateScreen(0)
    object Loading : StateScreen(1)
    object Done : StateScreen(2)
}

@Composable
fun LoginScreen(navHostController: NavHostController) {
    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current
    fun hideKeyboard() = keyboardController?.hide()

    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    var stateScreen by remember { mutableIntStateOf(StateScreen.Init.id) }

    LaunchedEffect(stateScreen) {
        when (stateScreen) {
            StateScreen.Init.id -> {}
            StateScreen.Done.id -> {
                navHostController.navigate(Screen.Info.route)
            }

            StateScreen.Loading.id -> {}
        }
    }

    ColumnCenterItem(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickOutsideListener {
                hideKeyboard()
            }
    ) {

        OutlinedTextField(
            value = userName,
            onValueChange = {
                if (it.length > USER_NAME_MAX_LENGTH) {
                    return@OutlinedTextField
                }
                userName = it
            },
            modifier = Modifier.padding(16.dp),
            label = { Text("UserName") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
            ),
            supportingText = {
                Text(
                    text = "${userName.length}/$USER_NAME_MAX_LENGTH", fontSize = 12.sp
                )
            })

        OutlinedTextField(
            value = password,
            onValueChange = { it ->
                val number = it.toIntOrNull()
                if (number != null) {
                    if (it.length >= PASS_WORD_MAX_LENGTH) {
                        return@OutlinedTextField
                    }
                    password = it
                }
            },
            singleLine = true,
            modifier = Modifier.padding(16.dp),
            label = { Text("PassWord") },
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        modifier = Modifier.size(24.dp), painter = painterResource(
                            if (passwordVisible) {
                                R.drawable.visibility
                            } else {
                                R.drawable.hide
                            }
                        ), contentDescription = null
                    )
                }
            },
            supportingText = {
                Text(
                    text = "${password.length}/$PASS_WORD_MAX_LENGTH", fontSize = 12.sp
                )
            },
            keyboardActions = KeyboardActions(onDone = {
                if (validate(context, userName, password)) {
                    hideKeyboard()
                    stateScreen = StateScreen.Loading.id
                } else {
                    context.showToast("UserName or PassWord is not correct!")
                }
                hideKeyboard()
            })
        )

        when (stateScreen) {
            StateScreen.Done.id, StateScreen.Init.id -> {
                BaseButtonNext(onClick = {
                    if (validate(context, userName, password)) {
                        hideKeyboard()
                        stateScreen = StateScreen.Loading.id
                    } else {
                        context.showToast("UserName or PassWord is not correct!")
                    }
                }, text = "Login")
            }

            StateScreen.Loading.id -> {
                CircularProgressIndicator()
                LaunchedEffect(Unit) {
                    coroutineScope.launch(Dispatchers.IO) {
                        delay(2000L)
                        stateScreen = StateScreen.Done.id
                    }
                }
            }
        }
    }
}

private fun validate(context: Context, userName: String, password: String): Boolean {
    if (userName.isEmpty() || password.isEmpty()) {
        context.showToast("UserName or PassWord must not empty!")
        return false
    }

    if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
        context.showToast("UserName must is email.")
        return false
    }
    return userName.length > "@gmail.com".length && password.length >= 4
}