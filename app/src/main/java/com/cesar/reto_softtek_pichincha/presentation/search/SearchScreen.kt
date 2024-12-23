package com.cesar.reto_softtek_pichincha.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cesar.domain.model.Recipe
import com.cesar.reto_softtek_pichincha.Categories
import com.cesar.reto_softtek_pichincha.presentation.list.ItemRecipe
import com.cesar.reto_softtek_pichincha.ui.theme.AppTheme
import com.cesar.reto_softtek_pichincha.ui.theme.Red2
import com.cesar.reto_softtek_pichincha.ui.theme.Red4
import com.cesar.reto_softtek_pichincha.viewmodel.ListViewModel
import com.cesar.reto_softtek_pichincha.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel : SearchViewModel = hiltViewModel(),
    list:List<Recipe>,
    onBack:()->Unit,
    onDetail:(Recipe)->Unit) {

    LaunchedEffect(key1 = true){
        viewModel.updateList(list)
    }
    
    Scaffold(
        containerColor = AppTheme.colorScheme.onBackground,
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ){
                        Image(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Sharp.KeyboardArrowLeft,
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(color = Color.White)
                        )
                    }
                },
                title = {
                    Text(
                        text = "Buscar Recetas",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Red2
                )
            )
        }

    ) {
            Column(
                Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize()
            ) {
                Box(
                    Modifier
                        .padding(10.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(text = "Buscar",
                                style = TextStyle(color = Color.LightGray, fontSize = 18.sp))
                        },
                        value = viewModel.stateElements.search?:"",
                        onValueChange = {value->
                            viewModel.updateTextSearch(value)
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend ={

                        }),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.White,
                            focusedBorderColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(20.dp),
                        textStyle = TextStyle(color = Color.DarkGray, fontSize = 18.sp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                ) {
                    viewModel.stateElements.listFilter?.let { list ->
                        items(list){recipe->
                            ItemRecipe(
                                recipe = recipe,
                                showStar = false,
                                onDetail = {
                                    onDetail(it)
                                }
                            )
                        }
                    }
                }
            }
    }



}

@Composable
@Preview
fun SearchScreenPreview() {
//    SearchScreen()
}