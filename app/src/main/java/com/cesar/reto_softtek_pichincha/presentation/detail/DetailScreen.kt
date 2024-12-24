package com.cesar.reto_softtek_pichincha.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cesar.domain.model.Recipe
import com.cesar.reto_softtek_pichincha.R
import com.cesar.reto_softtek_pichincha.ui.theme.AppTheme
import com.cesar.reto_softtek_pichincha.ui.theme.grayLight
import com.cesar.reto_softtek_pichincha.ui.theme.grayTransparent60
import com.cesar.reto_softtek_pichincha.ui.theme.grayTransparent75

@Composable
fun DetailScreen(recipe: Recipe, onBack:()->Unit) {
    Scaffold(
        containerColor = AppTheme.colorScheme.onBackground
    ) {
        Column(
            Modifier.padding(top = it.calculateTopPadding())
                .verticalScroll(rememberScrollState())
        ) {
            ConstraintLayout {
                val (image,boxIvBack) = createRefs()

                AsyncImage(
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .constrainAs(image) {
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        },
                    contentScale = ContentScale.FillHeight,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.image)
                        .placeholder(R.drawable.image_placeholder)
                        .crossfade(2000)
                        .build(),
                    contentDescription = recipe.name
                )

                Box(
                    Modifier
                        .constrainAs(boxIvBack) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                        .padding(start = 10.dp, top = 10.dp)
                ) {
                    Image(
                        imageVector = Icons.Sharp.KeyboardArrowLeft,
                        contentDescription = "back",
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(color = Color.White),
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color = grayTransparent60)
                            .clickable {
                                onBack()
                            }
                    )
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text = recipe.name,
                    style = TextStyle(
                        color = AppTheme.colorScheme.colorTextSubtitle,
                        fontSize = 25.sp
                    ),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Ingredientes",
                    style = TextStyle(
                        color = AppTheme.colorScheme.colorTextSubtitle,
                        fontSize = 18.sp
                    ),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = AppTheme.colorScheme.colorBackgroundCard,
                        contentColor = AppTheme.colorScheme.colorBackgroundCard
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(
                        Modifier.padding(10.dp)
                    ) {
                        recipe.ingredients.forEach {
                            ItemCard(it)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Pasos",
                    style = TextStyle(
                        color = AppTheme.colorScheme.colorTextSubtitle,
                        fontSize = 18.sp
                    ),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = AppTheme.colorScheme.colorBackgroundCard,
                        contentColor = AppTheme.colorScheme.colorBackgroundCard
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(
                        Modifier.padding(10.dp)
                    ) {
                        recipe.steps.forEach {
                            ItemCard(it)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}