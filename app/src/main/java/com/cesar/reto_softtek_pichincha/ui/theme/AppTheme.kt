package com.cesar.reto_softtek_pichincha.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val darkColorScheme = AppColorScheme(
    onBackground = Color.Black,
    colorText = Color.White,
    colorTextTitle = Color.White,
    colorTextSubtitle = Color.White,
    colorBackgroundCard = backLight,
    colorIcon = Color.White,
)

private val lightColorScheme = AppColorScheme(
    onBackground = Color.White,
    colorText = Color.DarkGray,
    colorTextTitle = Color.DarkGray,
    colorTextSubtitle = Color.Gray,
    colorBackgroundCard = grayLight,
    colorIcon = Color.Black,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        localAppColorScheme provides colorScheme,
        LocalIndication provides rippleIndication,
        content = content
    )
}

object AppTheme {
    val colorScheme : AppColorScheme
        @Composable get()= localAppColorScheme.current
}