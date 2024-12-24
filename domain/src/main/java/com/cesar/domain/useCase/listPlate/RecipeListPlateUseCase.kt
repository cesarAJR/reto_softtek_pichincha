package com.cesar.domain.useCase.listPlate

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeListPlateUseCase {
    suspend fun execute(): Flow<Result<List<Recipe>>>

}