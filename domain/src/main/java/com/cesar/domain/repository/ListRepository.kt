package com.cesar.domain.repository

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    suspend fun getRecipes(): Flow<Result<List<Recipe>>>
}