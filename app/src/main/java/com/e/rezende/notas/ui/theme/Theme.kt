package com.e.rezende.notas.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = RedPink,
    secondary = DarkGray,
    tertiary = BabyBlue,
    background = DarkGray,
    surface = White,
    onBackground = DarkGray,
    onSurface = DarkGray,
)

private val LightColorScheme = lightColorScheme(
    primary = RedPink,
    secondary = White,
    tertiary = BabyBlue,
    background = WhiteVariant,
    surface = White,
    onBackground = WhiteVariant,
    onSurface = DarkGray,
)

@Composable
fun NOTASTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    val view = LocalView.current

    SideEffect {
        val activity = view.context as Activity
        activity.window.statusBarColor = DarkColorScheme.background.toArgb()
        activity.window.navigationBarColor = DarkColorScheme.background.toArgb()

        WindowCompat.getInsetsController(activity.window, activity.window.decorView).apply {
            isAppearanceLightStatusBars = darkTheme
            isAppearanceLightNavigationBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}