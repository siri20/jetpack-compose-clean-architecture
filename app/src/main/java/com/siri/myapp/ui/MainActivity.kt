package com.siri.myapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import com.siri.core.ui.theme.JetShopeeTheme
import com.siri.myapp.ui.interview.MyPracticeMainApp
import com.siri.user.ui.splash.SplashScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetShopeeTheme {
                Surface {
                    val windowSize = calculateWindowSizeClass(this)
                    var showSplashScreen by remember { mutableStateOf(true) }
                    if (showSplashScreen) {
                        SplashScreen(onTimeout = { showSplashScreen = false })
                    } else {
                        MyPracticeMainApp(windowSize = windowSize.widthSizeClass)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PracticePreview() {
    JetShopeeTheme {
        Surface {
            MyPracticeMainApp(windowSize = WindowWidthSizeClass.Compact)
        }
    }
}