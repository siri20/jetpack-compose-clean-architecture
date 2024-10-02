package com.siri.myapp.ui.interview

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.siri.myapp.utils.MyPracticeNavigationType

@Composable
fun MyPracticeMainApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    MyPracticeScreen(modifier = modifier)
}