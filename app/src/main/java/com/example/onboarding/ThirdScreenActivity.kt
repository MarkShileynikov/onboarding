package com.example.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.onboarding.ui.Screen
import com.example.onboarding.ui.theme.thirdScreenColor

class ThirdScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen(
                backgroundColor = thirdScreenColor,
                headerTextId = R.string.third_screen_header,
                textId = R.string.third_screen_text,
                imageId = R.drawable.img_car3,
                navigationBarId = R.drawable.navigation_3,
                buttonId = R.drawable.loader_3,
                moveToNextActivity = {
                    startActivity(Intent(this, FourthScreenActivity::class.java))
                }
            )
        }
    }
}