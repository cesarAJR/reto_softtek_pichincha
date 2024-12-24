package com.cesar.reto_softtek_pichincha.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesar.domain.model.Recipe
import com.cesar.domain.useCase.search.RecipeSearchUseCase
import com.cesar.reto_softtek_pichincha.presentation.list.ListUiState
import com.cesar.reto_softtek_pichincha.presentation.search.SearchElements
import com.cesar.reto_softtek_pichincha.presentation.search.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel@Inject constructor(val useCase: RecipeSearchUseCase) : ViewModel() {
    var stateElements by mutableStateOf(SearchElements())

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Nothing)
    val uiState: StateFlow<SearchUiState> = _uiState
    fun updateList(list:List<Recipe>){
        stateElements= stateElements.copy(list=list)
    }

    fun updateTextSearch(search:String){
        stateElements= stateElements.copy(search =search)
        if (search.isNotEmpty()) getSearch() else _uiState.value = SearchUiState.Success(listOf())
    }

    fun getSearch(){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(stateElements.search!!)
                .collect{r->
                    Log.d("to------",r.data.toString())
                        _uiState.value = SearchUiState.Success(r.data)
                }
        }
    }
}