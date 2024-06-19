package com.example.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onboarding.ui.Screen
import com.example.onboarding.ui.theme.firstScreenColor
import com.example.onboarding.ui.theme.fourthScreenColor
import com.example.onboarding.ui.theme.secondScreenColor
import com.example.onboarding.ui.theme.thirdScreenColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "screen_1",
                enterTransition = {
                    fadeIn(animationSpec = tween(500)) + slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, tween(500)
                    )
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(500)) + slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left, tween(500)
                    )
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(500)) + slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, tween(500)
                    )
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(500)) + slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right, tween(500)
                    )
                }
            ) {
                composable("screen_1") {
                    FirstScreen(navController = navController)
                }
                composable("screen_2") {
                    SecondScreen(navController = navController)
                }
                composable("screen_3") {
                    ThirdScreen(navController = navController)
                }
                composable("screen_4") {
                    FourthScreen(navController = navController)
                }
                composable("screen_5") {
                    LastScreen(navController = navController)
                }
            }
        }
    }

    @Composable
    fun FirstScreen(navController: NavHostController) {
        Screen(
            backgroundColor = firstScreenColor,
            headerTextId = R.string.first_screen_header,
            textId = R.string.first_screen_text,
            imageId = R.drawable.img_car1,
            navigationBarId = R.drawable.navigation_1,
            buttonId = R.drawable.loader_1,
            moveToNextActivity = {
                navController.navigate("screen_2")
            },
            moveToLastScreen = {
                navController.navigate("screen_5")
            },
            moveToPreviousActivity = {
                navController.popBackStack()
            }
        )
    }

    @Composable
    fun SecondScreen(navController: NavHostController) {
        Screen(
            backgroundColor = secondScreenColor,
            headerTextId = R.string.second_screen_header,
            textId = R.string.second_screen_text,
            imageId = R.drawable.img_car2,
            navigationBarId = R.drawable.navigation_2,
            buttonId = R.drawable.loader_2,
            moveToNextActivity = {
                navController.navigate("screen_3")
            },
            moveToLastScreen = {
                navController.navigate("screen_5")
            },
            moveToPreviousActivity = {
                navController.popBackStack()
            }
        )
    }

    @Composable
    fun ThirdScreen(navController: NavHostController) {
        Screen(
            backgroundColor = thirdScreenColor,
            headerTextId = R.string.third_screen_header,
            textId = R.string.third_screen_text,
            imageId = R.drawable.img_car3,
            navigationBarId = R.drawable.navigation_3,
            buttonId = R.drawable.loader_3,
            moveToNextActivity = {
                navController.navigate("screen_4")
            },
            moveToLastScreen = {
                navController.navigate("screen_5")
            },
            moveToPreviousActivity = {
                navController.popBackStack()
            }
        )
    }

    @Composable
    fun FourthScreen(navController: NavHostController) {
        Screen(
            backgroundColor = fourthScreenColor,
            headerTextId = R.string.fourth_screen_header,
            textId = R.string.fourth_screen_text,
            imageId = R.drawable.img_car4,
            navigationBarId = R.drawable.navigation_4,
            buttonId = R.drawable.loader_4,
            moveToNextActivity = {
                navController.navigate("screen_5")
            },
            moveToLastScreen = {
                navController.navigate("screen_5")
            },
            moveToPreviousActivity = {
                navController.popBackStack()
            }
        )
    }

    @Composable
    fun LastScreen(navController: NavHostController) {
        var offsetX by remember { mutableStateOf(0f) }
        var isTransitionTriggered by remember { mutableStateOf(false) }

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val swipeThreshold = screenWidth * 0.6f
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(fourthScreenColor)
                .padding(24.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        if (!isTransitionTriggered) {
                            change.consume()
                            offsetX += dragAmount
                            if (offsetX > swipeThreshold.value) {
                                isTransitionTriggered = true
                                navController.popBackStack()
                            }
                        }
                    }
                }
        ) {
            Text(
                text = stringResource(id = R.string.last_screen_header),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}