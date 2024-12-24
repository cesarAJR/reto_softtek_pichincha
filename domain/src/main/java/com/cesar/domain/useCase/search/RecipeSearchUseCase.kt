package com.cesar.domain.useCase.search

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeSearchUseCase {
    suspend fun execute(search:String): Flow<Result<List<Recipe>>>

}