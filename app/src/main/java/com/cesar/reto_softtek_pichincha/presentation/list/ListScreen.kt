package com.cesar.reto_softtek_pichincha.presentation.list

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cesar.domain.model.Recipe
import com.cesar.reto_softtek_pichincha.Categories
import com.cesar.reto_softtek_pichincha.component.DialogLoading
import com.cesar.reto_softtek_pichincha.ui.theme.AppTheme
import com.cesar.reto_softtek_pichincha.ui.theme.Red2
import com.cesar.reto_softtek_pichincha.viewmodel.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel : ListViewModel = hiltViewModel(),
    onDetail:(Recipe)->Unit,
    onSearch:(List<Recipe>)->Unit,
) {
    val context = LocalContext.current

    var loading by remember {
        mutableStateOf(false)
    }

    var updateFavorites by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true){
        viewModel.getRecipes()
    }



    val list = mutableListOf(
        Categories.ALL.description,
        Categories.DESSERTS.description,
        Categories.PLATES.description,
        Categories.FAVORITE.description
    )

    Scaffold(
        containerColor = AppTheme.colorScheme.onBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Recetas",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Red2
                ),
                actions = {
                    Row{
                        Icon(
                            imageVector = Icons.Sharp.Search,
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(30.dp)
                                .clickable {
                                    onSearch(viewModel.stateElements.list ?: listOf())
                                }
                        )
                    }
                }
            )
        }

    ) {
                Column(modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow {
                        list.let {
                            items(it){
                                Spacer(modifier = Modifier.width(10.dp))
                                ItemCategory(category = it,viewModel.stateElements.categorie?:""){
                                    viewModel.updateCategorie(it)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(
                        contentPadding = PaddingValues(8.dp),
                    ) {
                        viewModel.stateElements.listRecipesCategorie?.let { list ->
                            items(list){recipe->
                                ItemRecipe(recipe = recipe,viewModel.stateElements.categorie, onDetail = {
                                    onDetail(it)
                                })
                            }
                        }
                    }

                }

        }




    if (loading){
        DialogLoading(true)
    }else{
        DialogLoading(false)
    }

    LaunchedEffect(Unit){
        viewModel.uiState.collect{
            when(it) {
                is ListUiState.Error -> {
                    loading=false
                    Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                }
                is ListUiState.Loading -> {
                    loading=true
                }
                is ListUiState.Nothing -> {}
                is ListUiState.Success -> {
                    loading=false
                    it.list?.let { it1->viewModel.updateList(it1) }

                }
            }
        }
    }

}

@Composable
@Preview
fun ListScreenPreview(){
//    ListScreen()
}