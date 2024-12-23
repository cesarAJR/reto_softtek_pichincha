package com.cesar.reto_softtek_pichincha.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesar.domain.model.Recipe
import com.cesar.domain.useCase.list.RecipeListUseCase
import com.cesar.reto_softtek_pichincha.Categories
import com.cesar.reto_softtek_pichincha.presentation.list.ListElements
import com.cesar.reto_softtek_pichincha.presentation.list.ListUiState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel@Inject constructor(val useCase: RecipeListUseCase) : ViewModel() {
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


    fun updateList(list:List<Recipe>){
        stateElements= stateElements.copy(list=list)
        filterByCategorie()
    }

    fun updateCategorie(categorie:String){
        stateElements= stateElements.copy(categorie=categorie)
        when (categorie) {
            Categories.ALL.description -> {
                stateElements= stateElements.copy(listRecipesCategorie = stateElements.list)
            }
            Categories.DESSERTS.description -> {
                val filter = stateElements.list?.filter {
                    it.type == "1"
                }
                stateElements= stateElements.copy(listRecipesCategorie = filter)
            }
            Categories.PLATES.description -> {
                val filter = stateElements.list?.filter {
                    it.type == "2"
                }
                stateElements= stateElements.copy(listRecipesCategorie = filter)
            }
            Categories.FAVORITE.description -> {
                val filter = stateElements.list?.filter {
                    it.favorite
                }
                stateElements= stateElements.copy(listRecipesCategorie = filter)
            }
        }
    }

    private fun filterByCategorie(){
        stateElements= stateElements.copy(listRecipesCategorie = stateElements.list)
    }




}