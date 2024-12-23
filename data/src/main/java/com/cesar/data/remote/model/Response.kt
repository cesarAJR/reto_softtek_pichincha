package com.cesar.data.remote.model

import com.cesar.domain.model.Recipe
import com.google.gson.annotations.SerializedName
data class DataResponse(
    @SerializedName("data")
    val data : List<RecipeResponse>,
)

data class RecipeResponse(
    @SerializedName("id")
    val id : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("image")
    val image : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("type")
    val type : String?=null,
    @SerializedName("detail")
    val detail : RecipeDetailResponse?=null
)

data class RecipeDetailResponse(
    @SerializedName("ingredients")
    val ingredients : List<String>,
    @SerializedName("steps")
    val steps : List<String>
)


fun List<RecipeResponse>.toListRecipe():List<Recipe> = map{
    Recipe(
       name = it.name,
       image = it.image,
       description = it.description,
       favorite  = false,
       type = it.type?:"",
        ingredients = it.detail?.ingredients?: listOf(),
        steps = it.detail?.steps?: listOf(),
    )
}