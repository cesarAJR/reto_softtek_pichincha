package com.cesar.domain.useCase.listPlate

import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import com.cesar.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeListPlateUseCaseImp@Inject constructor(private val repository: ListRepository) : RecipeListPlateUseCase {
    override suspend fun execute(): Flow<Result<List<Recipe>>> {
        return repository.getListPlate()
    }
}