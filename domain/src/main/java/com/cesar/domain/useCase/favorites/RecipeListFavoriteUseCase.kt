package com.cesar.domain.useCase.favorites

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeListFavoriteUseCase {
    suspend fun execute(): Flow<Result<List<Recipe>>>

}