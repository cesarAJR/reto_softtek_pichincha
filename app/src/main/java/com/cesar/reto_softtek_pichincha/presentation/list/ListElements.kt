package com.cesar.reto_softtek_pichincha.presentation.list

import com.cesar.domain.model.Recipe

data class ListElements(
    val list : List<Recipe>?=null,
    val listRecipesCategorie : List<Recipe>?=null,
    val categorie : String ="Todos",
)
