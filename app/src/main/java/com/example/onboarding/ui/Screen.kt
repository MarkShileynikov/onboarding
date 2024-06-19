package com.example.onboarding.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onboarding.R

@Composable
fun Screen(
    backgroundColor: Color,
    headerTextId: Int,
    textId: Int,
    imageId: Int,
    navigationBarId: Int,
    buttonId: Int,
    moveToNextActivity: () -> Unit,
    moveToLastScreen: () -> Unit
) {
    val context = LocalContext.current
    var offsetX by remember { mutableStateOf(0f) }
    var isTransitionTriggered by remember { mutableStateOf(false) } // Флаг для отслеживания начала перехода

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val swipeThreshold = screenWidth * 0.6f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (!isTransitionTriggered) {
                        change.consume()
                        offsetX += dragAmount

                        if (offsetX < -swipeThreshold.value) {
                            isTransitionTriggered = true
                            moveToNextActivity()
                        }
                    }
                }
            }
    ) {
        Text(
            color = Color.White,
            fontSize = 24.sp,
            text = stringResource(id = headerTextId),
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .padding(top = 83.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
        )
        Text(
            color = Color.White,
            fontSize = 24.sp,
            text = stringResource(id = textId),
            fontFamily = FontFamily.Default,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .offset(y = 16.dp)
        )
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Image(
                    painter = painterResource(id = navigationBarId),
                    contentDescription = "navigation",
                    modifier = Modifier
                        .padding(start = 24.dp, top = 16.dp)
                        .size(64.dp)
                )
                Text(
                    color = Color.White,
                    fontSize = 24.sp,
                    text = stringResource(id = R.string.skip),
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .clickable {
                            moveToLastScreen()
                        }
                )
            }
            Image(
                painter = painterResource(id = buttonId),
                contentDescription = "button",
                modifier = Modifier
                    .padding(top = 32.dp, end = 24.dp)
                    .size(72.dp)
                    .clickable {
                        moveToNextActivity()
                    }
            )
        }
    }
}