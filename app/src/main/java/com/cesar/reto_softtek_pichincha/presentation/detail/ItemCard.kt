package com.cesar.reto_softtek_pichincha.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesar.reto_softtek_pichincha.R
import com.cesar.reto_softtek_pichincha.ui.theme.AppTheme

@Composable
fun ItemCard(text:String) {

    Row {
        Image(
            modifier = Modifier
                .padding(top = 5.dp)
                .size(10.dp),
            painter = painterResource(id = R.drawable.ic_circle),
            colorFilter = ColorFilter.tint(color = AppTheme.colorScheme.colorIcon),
            contentDescription = "point",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = text,
            style = TextStyle(
                color = AppTheme.colorScheme.colorTextSubtitle,
                fontSize = 18.sp
            )
        )
    }

    Spacer(modifier = Modifier.height(5.dp))
}