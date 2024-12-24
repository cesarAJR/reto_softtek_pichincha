package com.cesar.domain.useCase.updateRecipe

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface UpdateRecipeUseCase {
    suspend fun execute(recipe: Recipe)

}