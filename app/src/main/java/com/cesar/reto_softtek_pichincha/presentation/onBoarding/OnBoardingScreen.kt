package com.cesar.reto_softtek_pichincha.presentation.onBoarding

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesar.domain.model.OnBoard
import com.cesar.reto_softtek_pichincha.MainActivity
import com.cesar.reto_softtek_pichincha.R
import com.cesar.reto_softtek_pichincha.ui.theme.AppTheme
import com.cesar.reto_softtek_pichincha.ui.theme.Red2
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(onList:()->Unit) {
    val context =  LocalContext.current

    val pages = listOf(
        OnBoard(
            title = "Listado de Recetas",
            description = "Aqui encontraras las opciones de busqueda como las categorias",
            image = R.drawable.onboarding1
        ),
        OnBoard(
            title = "Busqueda",
            description = "Busca por el nombre de la receta",
            image = R.drawable.onboarding2
        ),
        OnBoard(
            title = "Detalle",
            description = "Aqui encontraras los ingredientes como los pasos a seguir",
            image = R.drawable.onboarding3
        )
    )

    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            OnBoardItem(pages[page])
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {

            Text(
                "Saltar", style = TextStyle(
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                ),
                modifier = Modifier.clickable {
                    val skipPage = pagerState.pageCount-1
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(skipPage)
                    }
                }
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                repeat(pages.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .width(if (isSelected) 18.dp else 8.dp)
                            .height(if (isSelected) 8.dp else 8.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFF707784),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(
                                color = if (isSelected) Red2 else Color.White,
                                shape = CircleShape
                            )
                    )
                }
            }


            Text(
                "Siguiente", style = TextStyle(
                    color = AppTheme.colorScheme.colorTextTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                ),
                modifier = Modifier.clickable {
                    if (pagerState.currentPage < 2) {
                        val nextPage = pagerState.currentPage + 1
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(nextPage)
                        }
                    }else{
                        (context as MainActivity).updateFirstLauncher()
                        onList()
                    }
                }
            )

        }
        Spacer(modifier = Modifier.height(50.dp))
    }

}