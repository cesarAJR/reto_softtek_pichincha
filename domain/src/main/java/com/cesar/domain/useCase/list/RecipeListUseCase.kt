package com.cesar.domain.useCase.list

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeListUseCase {
    suspend fun execute(): Flow<Result<List<Recipe>>>

}