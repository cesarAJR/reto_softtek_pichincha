package com.cesar.reto_softtek_pichincha.presentation.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesar.reto_softtek_pichincha.ui.theme.AppTheme
import com.cesar.reto_softtek_pichincha.ui.theme.Red2
import com.cesar.reto_softtek_pichincha.ui.theme.Red3

@Composable
fun ItemCategory(category:String,categoryDefault: String,onChange:(String)->Unit) {

    var colorText = AppTheme.colorScheme.colorTextTitle
    var colorBorder = AppTheme.colorScheme.colorTextTitle
    var containerColor = Color.Transparent
    var contentColor = Color.White

    if (category== categoryDefault){
        colorText = Color.White
        colorBorder = Red2
        containerColor = Red2
    }


    Card(
        modifier = Modifier.clickable {
            onChange(category)
        },
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor =  contentColor,
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.5.dp, colorBorder)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = category,
            style = TextStyle(fontSize = 20.sp, color = colorText)
        )
    }

}

@Composable
@Preview
fun ItemCategoryPreview(){
//    ItemCategory("Todos")
}