package com.cesar.reto_softtek_pichincha.presentation.search

import com.cesar.domain.model.Recipe

data class SearchElements(
    val list : List<Recipe>?=null,
    val listFilter : List<Recipe>?=null,
    val search : String?="",
)
