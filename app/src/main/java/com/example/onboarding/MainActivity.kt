package com.example.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.onboarding.ui.Screen
import com.example.onboarding.ui.theme.firstScreenColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen(
                backgroundColor = firstScreenColor,
                headerTextId = R.string.first_screen_header,
                textId = R.string.first_screen_text,
                imageId = R.drawable.img_car1,
                navigationBarId = R.drawable.navigation_1,
                buttonId = R.drawable.loader_1
            )
        }
    }
}

