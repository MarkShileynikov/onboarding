package com.example.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.onboarding.ui.Screen
import com.example.onboarding.ui.theme.secondScreenColor

class SecondScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen(
                backgroundColor = secondScreenColor,
                headerTextId = R.string.second_screen_header,
                textId = R.string.second_screen_text,
                imageId = R.drawable.img_car2,
                navigationBarId = R.drawable.navigation_2,
                buttonId = R.drawable.loader_2,
                moveToNextActivity = {
                    startActivity(Intent(this, ThirdScreenActivity::class.java))
                }
            )
        }
    }
}