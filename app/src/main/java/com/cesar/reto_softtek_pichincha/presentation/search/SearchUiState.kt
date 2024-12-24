package com.cesar.reto_softtek_pichincha.presentation.search

import com.cesar.domain.model.Recipe

sealed class SearchUiState {

    data class Success(val list: List<Recipe>?): SearchUiState()

    object Nothing: SearchUiState()

}