package com.jin.composeexam.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.jin.composeexam.ui.theme.backgroundColor4
import com.jin.composeexam.ui.theme.primaryColor4

@Composable
fun ColumnCenterItem(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Composable
fun RowCenterItem(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun BoxCenterItem(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    Box(modifier, contentAlignment = Alignment.Center) {
        content()
    }
}

@Composable
fun BaseButtonNext(
    onClick: () -> Unit, text: String,
    backgroundColor: Color = backgroundColor4,
    textColor: Color = primaryColor4
) {
    ElevatedButton(onClick = {
        onClick()
    }, colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)) {
        Text(text = text, color = textColor, fontWeight = FontWeight.Bold)
    }
}