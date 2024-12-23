package com.cesar.domain.useCase.list

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import com.cesar.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeListUseCaseImp@Inject constructor(private val repository: ListRepository) : RecipeListUseCase {
    override suspend fun execute(): Flow<Result<List<Recipe>>> {
        return repository.getRecipes()
    }
}