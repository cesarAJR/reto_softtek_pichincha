package com.cesar.domain.useCase.updateRecipe

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import com.cesar.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateRecipeUseCaseImp@Inject constructor(private val repository: ListRepository) : UpdateRecipeUseCase {
    override suspend fun execute(recipe: Recipe) {
        return repository.updateRecipe(recipe)
    }
}