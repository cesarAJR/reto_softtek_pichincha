package com.cesar.reto_softtek_pichincha.presentation.list

import com.cesar.domain.model.Recipe

sealed class ListUiState {

    data class Success(val list: List<Recipe>?): ListUiState()
    data class Error(val message: String): ListUiState()
    object Loading: ListUiState()
    object Nothing: ListUiState()

}