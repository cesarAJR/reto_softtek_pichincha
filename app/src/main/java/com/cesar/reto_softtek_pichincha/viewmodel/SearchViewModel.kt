package com.cesar.reto_softtek_pichincha.viewmodel

import android.preference.PreferenceDataStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cesar.domain.model.Recipe
import com.cesar.domain.useCase.list.RecipeListUseCase
import com.cesar.reto_softtek_pichincha.presentation.search.SearchElements
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel@Inject constructor() : ViewModel() {
    var stateElements by mutableStateOf(SearchElements())

    fun updateList(list:List<Recipe>){
        stateElements= stateElements.copy(list=list)
    }

    fun updateTextSearch(search:String){
        stateElements= stateElements.copy(search =search)
        filter(search)
    }

    private fun filter(search:String){
        stateElements = if (search.isNotEmpty()){
            val filter = stateElements.list?.filter {
                it.name.contains(search,true)
            }
            stateElements.copy(listFilter = filter)
        }else
            stateElements.copy(listFilter = listOf())
    }
}