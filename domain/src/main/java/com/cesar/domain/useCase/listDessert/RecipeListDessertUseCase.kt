package com.cesar.domain.useCase.listDessert

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeListDessertUseCase {
    suspend fun execute(): Flow<Result<List<Recipe>>>

}