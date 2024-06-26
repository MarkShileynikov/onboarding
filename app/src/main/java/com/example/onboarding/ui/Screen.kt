package com.example.onboarding.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.constraintlayout.compose.Dimension
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
                        Log.d("offset", offsetX.toString())
                        if (offsetX < -swipeThreshold.value) {
                            isTransitionTriggered = true
                            moveToNextActivity()
                        } else if (offsetX > swipeThreshold.value && imageId != R.drawable.img_car1) {
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
            fontSize = 20.sp,
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
            fontSize = 16.sp,
            text = stringResource(id = textId),
            fontFamily = FontFamily.Default,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .constrainAs(text) {
                    top.linkTo(header.bottom)
                    start.linkTo(parent.start)
                }
        )

        Image(
            painter = painterResource(id = imageId),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 32.dp)
                .constrainAs(image) {
                    width = Dimension.wrapContent
                    height = Dimension.fillToConstraints
                    top.linkTo(text.bottom)
                    if (imageId == R.drawable.img_car1 || imageId == R.drawable.img_car4) {
                        start.linkTo(parent.start)
                    } else {
                        end.linkTo(parent.end)
                    }
                    bottom.linkTo(button.top)
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
                fontSize = 16.sp,
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