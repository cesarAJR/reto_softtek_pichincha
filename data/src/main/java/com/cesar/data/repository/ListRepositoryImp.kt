package com.cesar.data.repository

import com.cesar.data.remote.Methods
import com.cesar.data.remote.model.toListRecipe
import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import com.cesar.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListRepositoryImp @Inject constructor( private val methods: Methods) : ListRepository {
    override suspend fun getRecipes(): Flow<Result<List<Recipe>>> = flow {
       val result = methods.getRecipes()
        if (result==null){
            emit(Result.Error("error"))
        }else
          emit(Result.Successfull(result.data.toListRecipe()))
    }
}