package com.cesar.reto_softtek_pichincha.presentation.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesar.domain.model.OnBoard
import com.cesar.reto_softtek_pichincha.ui.theme.AppTheme

@Composable
fun OnBoardItem(onBoard: OnBoard) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = onBoard.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(300.dp)
                    .padding(bottom = 20.dp)
            )
            Text(
                text = onBoard.title, style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppTheme.colorScheme.colorTextTitle,
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = onBoard.description,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = AppTheme.colorScheme.colorTextSubtitle,
                    textAlign = TextAlign.Center,
                )
            )

        }


}