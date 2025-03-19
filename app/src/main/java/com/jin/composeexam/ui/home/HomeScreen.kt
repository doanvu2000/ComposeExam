package com.jin.composeexam.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jin.composeexam.R
import com.jin.composeexam.util.Constants

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Welcome, ${Constants.displayName}",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(Modifier.padding(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.me), null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Inside
            )
        }

        Text(
            "Gender: ${Constants.gender}",
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            "Province: ${Constants.result}",
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}