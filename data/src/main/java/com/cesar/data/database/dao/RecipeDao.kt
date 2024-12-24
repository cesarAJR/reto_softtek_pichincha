package com.cesar.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cesar.data.database.model.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipes : List<RecipeEntity>)

    @Query("SELECT * FROM table_recipe")
    fun getAllRecipes() : List<RecipeEntity>

    @Query("SELECT * FROM table_recipe where table_recipe.favorite = 1 ")
    fun getFavorites() : Flow<List<RecipeEntity>>

    @Query("SELECT * FROM table_recipe where table_recipe.type ='2'")
    fun getPlates() : Flow<List<RecipeEntity>>

    @Query("SELECT * FROM table_recipe where table_recipe.type ='1'")
    fun getDesserts() : Flow<List<RecipeEntity>>

    @Query("SELECT * FROM table_recipe where table_recipe.name LIKE '%' || :search || '%'")
    fun getSearchRecipes(search:String) : List<RecipeEntity>

    @Update
    fun update(recipe: RecipeEntity)
}