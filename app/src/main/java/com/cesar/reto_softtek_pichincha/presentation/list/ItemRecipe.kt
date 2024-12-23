package com.cesar.reto_softtek_pichincha.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.cesar.reto_softtek_pichincha.Categories
import com.cesar.reto_softtek_pichincha.R
import com.cesar.reto_softtek_pichincha.ui.theme.grayTransparent75


@Composable
fun ItemRecipe(recipe: Recipe,
               categorieDefault:String?=null,
               showStar:Boolean?=true,
               onDetail:(Recipe)->Unit,
) {
   var painterStart =  painterResource(id = R.drawable.star_off)

    var favorite by remember {
        mutableStateOf(recipe.favorite)
    }

    if (favorite){
        painterStart =  painterResource(id = R.drawable.star_on)
    }

    var show by remember {
        mutableStateOf(true)
    }

    if (categorieDefault==Categories.FAVORITE.description && !favorite){
        show = false
    }
    
    if (show){
        Column {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.clickable {
                    onDetail(recipe)
                }
            ) {

                ConstraintLayout(
                    Modifier.fillMaxWidth()
                ) {
                    val (image,icStar,column) = createRefs()

                    AsyncImage(
                        modifier = Modifier
                            .height(400.dp)
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
                if (showStar==true){
                    IconButton(
                        onClick = {
                            favorite = !recipe.favorite
                            recipe.favorite = !recipe.favorite
                        },
                        modifier = Modifier.constrainAs(icStar){
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                    ){
                        Image(
                            painter = painterStart,
                            contentDescription = "star",
                            colorFilter = ColorFilter.tint(Color.Yellow),
                            modifier = Modifier
                                .size(70.dp)
                        )
                    }
                }


                    Column(
                        Modifier
                            .background(color = grayTransparent75)
                            .padding(start = 10.dp, end = 10.dp)
                            .constrainAs(column) {
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            modifier = Modifier.align(Alignment.Start).fillMaxWidth(),
                            text = recipe.name,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp
                            ),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = recipe.description,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }




}

@Composable
@Preview
fun ItemRecipePreview(){

//    ItemRecipe(recipe, context = LocalContext.current)
}