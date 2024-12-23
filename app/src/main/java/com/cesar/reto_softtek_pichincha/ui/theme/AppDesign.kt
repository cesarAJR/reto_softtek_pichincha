package com.cesar.reto_softtek_pichincha.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColorScheme(
    val onBackground: Color,
    val colorText: Color,
    val colorTextTitle: Color,
    val colorTextSubtitle: Color,
    val colorBackgroundCard:Color,
    val colorIcon:Color,
)

val localAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        onBackground = Color.Unspecified,
        colorText = Color.Unspecified,
        colorTextTitle = Color.Unspecified,
        colorTextSubtitle = Color.Unspecified,
        colorBackgroundCard = Color.Unspecified,
        colorIcon = Color.Unspecified,
    )
}