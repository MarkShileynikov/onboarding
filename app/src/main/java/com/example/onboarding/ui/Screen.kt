/*
package com.example.onboarding.ui

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
    moveToLastScreen: () -> Unit,
    moveToPreviousActivity: () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var isTransitionTriggered by remember { mutableStateOf(false) }

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
                        } else if (offsetX > swipeThreshold.value) {
                            isTransitionTriggered = true
                            moveToPreviousActivity()
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
                .padding(top = 64.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
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
}*/
package com.example.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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
    moveToLastScreen: () -> Unit,
    moveToPreviousActivity: () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var isTransitionTriggered by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val swipeThreshold = screenWidth * 0.6f

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (!isTransitionTriggered) {
                        change.consume()
                        offsetX += dragAmount

                        if (offsetX < -swipeThreshold.value) {
                            isTransitionTriggered = true
                            moveToNextActivity()
                        } else if (offsetX > swipeThreshold.value) {
                            isTransitionTriggered = true
                            moveToPreviousActivity()
                        }
                    }
                }
            }
    ) {
        val (header, text, image, navigationColumn, button) = createRefs()

        Text(
            color = Color.White,
            fontSize = 24.sp,
            text = stringResource(id = headerTextId),
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .padding(top = 64.dp, start = 24.dp, end = 24.dp, bottom = 16.dp)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            color = Color.White,
            fontSize = 24.sp,
            text = stringResource(id = textId),
            fontFamily = FontFamily.Default,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .constrainAs(text) {
                    top.linkTo(header.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Image(
            painter = painterResource(id = imageId),
            contentDescription = "image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .constrainAs(image) {
                    top.linkTo(text.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Column(
            modifier = Modifier
                .constrainAs(navigationColumn) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        ) {
            Image(
                painter = painterResource(id = navigationBarId),
                contentDescription = "navigation",
                modifier = Modifier
                    .padding(top = 16.dp, start = 24.dp)
                    .size(64.dp)
            )
            Text(
                color = Color.White,
                fontSize = 24.sp,
                text = stringResource(id = R.string.skip),
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .padding(start = 24.dp, bottom = 32.dp)
                    .clickable {
                        moveToLastScreen()
                    }
            )
        }

        Image(
            painter = painterResource(id = buttonId),
            contentDescription = "button",
            modifier = Modifier
                .padding(top = 32.dp, end = 24.dp, bottom = 16.dp)
                .size(72.dp)
                .clickable {
                    moveToNextActivity()
                }
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                }
        )
    }
}