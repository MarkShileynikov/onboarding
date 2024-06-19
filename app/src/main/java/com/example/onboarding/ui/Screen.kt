package com.example.onboarding.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.onboarding.LastScreenActivity
import com.example.onboarding.R

@Composable
fun Screen(
    backgroundColor: Color,
    headerTextId: Int,
    textId: Int,
    imageId: Int,
    navigationBarId: Int,
    buttonId: Int,
    moveToNextActivity: () -> Unit)

{
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
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
                            context.startActivity(Intent(context, LastScreenActivity::class.java))
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