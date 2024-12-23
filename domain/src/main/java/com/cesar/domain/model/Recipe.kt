package com.cesar.domain.model

data class Recipe(
    val name: String,
    val image: String,
    val description: String,
    var favorite:Boolean=false,
    val type : String,
    val ingredients:List<String>,
    val steps:List<String>
)
