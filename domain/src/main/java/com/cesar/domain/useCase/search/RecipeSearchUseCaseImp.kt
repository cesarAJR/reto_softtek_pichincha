package com.cesar.domain.useCase.search

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import com.cesar.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeSearchUseCaseImp@Inject constructor(private val repository: ListRepository) : RecipeSearchUseCase {
    override suspend fun execute(search:String): Flow<Result<List<Recipe>>> {
        return repository.getSearch(search)
    }
}