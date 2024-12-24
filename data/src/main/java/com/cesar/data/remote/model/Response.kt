package com.cesar.data.remote.model

import com.cesar.data.database.model.RecipeEntity
import com.cesar.domain.model.Recipe
import com.google.gson.Gson
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
       id = it.id,
       name = it.name,
       image = it.image,
       description = it.description,
       favorite  = false,
       type = it.type?:"",
        ingredients = it.detail?.ingredients?: listOf(),
        steps = it.detail?.steps?: listOf(),
    )
}

fun List<RecipeResponse>.toListLocalRecipe():List<RecipeEntity> = map{
    val ingredients = it.detail?.ingredients?:listOf()
    val steps = it.detail?.steps?:listOf()

    RecipeEntity(
        id = it.id,
        name = it.name,
        image = it.image,
        description = it.description,
        favorite  = false,
        type = it.type?:"",
        ingredients = Gson().toJson(ingredients),
        steps = Gson().toJson(steps),
    )
}