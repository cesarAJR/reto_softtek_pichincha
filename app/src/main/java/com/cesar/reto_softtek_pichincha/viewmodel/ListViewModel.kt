package com.cesar.reto_softtek_pichincha.viewmodel

import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesar.domain.model.Recipe
import com.cesar.domain.useCase.favorites.RecipeListFavoriteUseCase
import com.cesar.domain.useCase.list.RecipeListUseCase
import com.cesar.domain.useCase.listDessert.RecipeListDessertUseCase
import com.cesar.domain.useCase.listPlate.RecipeListPlateUseCase
import com.cesar.domain.useCase.updateRecipe.UpdateRecipeUseCase
import com.cesar.reto_softtek_pichincha.Categories
import com.cesar.reto_softtek_pichincha.presentation.list.ListElements
import com.cesar.reto_softtek_pichincha.presentation.list.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class ListViewModel@Inject constructor(
    val useCase: RecipeListUseCase,
    val dessertUseCase: RecipeListDessertUseCase,
    val plateUseCase: RecipeListPlateUseCase,
    val favoriteUseCase: RecipeListFavoriteUseCase,
    val updateRecipeUseCase: UpdateRecipeUseCase,
) : ViewModel() {
    var stateElements by mutableStateOf(ListElements())

    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Nothing)
    val uiState: StateFlow<ListUiState> = _uiState

     fun getRecipes(){
         viewModelScope.launch(Dispatchers.IO) {
             _uiState.value = ListUiState.Loading
             useCase.execute()
                 .collect{r->
                     if (r.message!=null) _uiState.value = ListUiState.Error(r.message!!)
                     else
                     _uiState.value = ListUiState.Success(r.data)
                 }
         }
     }

    fun getListDessert(){
        viewModelScope.launch(Dispatchers.IO) {
            dessertUseCase.execute()
                .takeWhile { r ->
                    stateElements.categorie == Categories.DESSERTS.description
                }
                .collect{r->
                        _uiState.value = ListUiState.Success(r.data)
                }
        }
    }

    fun getListPlate(){
        viewModelScope.launch(Dispatchers.IO) {
            plateUseCase.execute()
                .takeWhile { r ->
                    stateElements.categorie == Categories.PLATES.description
                }
                .collect{r->
                  _uiState.value = ListUiState.Success(r.data)
                }

        }
    }

    fun getFavorites(){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteUseCase.execute()
                .takeWhile { r ->
                    stateElements.categorie == Categories.FAVORITE.description
                }
                .collect{r->
                    _uiState.value = ListUiState.Success(r.data)
                }
        }
    }

    fun updateFavorite(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            updateRecipeUseCase.execute(recipe)
        }
    }


    fun updateList(list:List<Recipe>){
        Log.d("to---3",list.toString())
        stateElements = stateElements.copy(list=list)
    }

    fun updateCategorie(categorie:String){
        stateElements= stateElements.copy(categorie=categorie)
        when (categorie) {
            Categories.ALL.description -> {
                getRecipes()
            }
            Categories.DESSERTS.description -> {
                getListDessert()
            }
            Categories.PLATES.description -> {
                getListPlate()
            }
            Categories.FAVORITE.description -> {
                getFavorites()
            }
        }
    }
}