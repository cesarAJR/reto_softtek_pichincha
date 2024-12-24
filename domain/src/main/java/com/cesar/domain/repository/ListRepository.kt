package com.cesar.domain.repository

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    suspend fun getRecipes(): Flow<Result<List<Recipe>>>

    suspend fun getSearch(search:String): Flow<Result<List<Recipe>>>
    suspend fun getListPlate(): Flow<Result<List<Recipe>>>
    suspend fun getListDessert(): Flow<Result<List<Recipe>>>
    suspend fun getFavorites(): Flow<Result<List<Recipe>>>
    suspend fun updateRecipe(recipe: Recipe)
}