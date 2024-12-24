package com.cesar.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cesar.domain.model.Recipe
import com.google.gson.Gson


@Entity(tableName = "table_recipe")
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id :String,
    @ColumnInfo(name = "name")
    val name :String,
    @ColumnInfo(name = "description")
    val description :String,
    @ColumnInfo(name = "image")
    val image :String,
    @ColumnInfo(name = "favorite")
    val favorite :Boolean,
    @ColumnInfo(name = "type")
    val type :String,
    @ColumnInfo(name = "ingredients")
    val ingredients :String,
    @ColumnInfo(name = "steps")
    val steps :String
)

fun List<RecipeEntity>.toListRecipe() : List<Recipe> = map {
    Recipe(
        id = it.id,
        name = it.name,
        image = it.image,
        description = it.description,
        favorite  = it.favorite,
        type = it.type,
        ingredients = Gson().fromJson(it.ingredients,Array<String>::class.java).toList(),
        steps = Gson().fromJson(it.steps,Array<String>::class.java).toList(),
    )
}

fun Recipe.toLocalRecipe(): RecipeEntity {
    val ingredients = this.ingredients
    val steps = this.steps

   return RecipeEntity(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        favorite  = this.favorite,
        type = this.type,
        ingredients = Gson().toJson(ingredients),
        steps = Gson().toJson(steps),
    )
}

fun RecipeEntity.toRecipe(): Recipe {


    return Recipe(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        favorite  = this.favorite,
        type = this.type,
        ingredients = Gson().fromJson(this.ingredients,Array<String>::class.java).toList(),
        steps = Gson().fromJson(this.steps,Array<String>::class.java).toList(),
    )
}
