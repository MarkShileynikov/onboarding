package com.example.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.onboarding.ui.Screen
import com.example.onboarding.ui.theme.fourthScreenColor

class FourthScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen(
                backgroundColor = fourthScreenColor,
                headerTextId = R.string.fourth_screen_header,
                textId = R.string.fourth_screen_text,
                imageId = R.drawable.img_car4,
                navigationBarId = R.drawable.navigation_4,
                buttonId = R.drawable.loader_4,
                moveToNextActivity = {
                    startActivity(Intent(this, LastScreenActivity::class.java))
                }
            )
        }
    }
}