package com.cesar.data.repository

import android.util.Log
import com.cesar.data.database.dao.RecipeDao
import com.cesar.data.database.model.toListRecipe
import com.cesar.data.database.model.toLocalRecipe
import com.cesar.data.database.model.toRecipe
import com.cesar.data.remote.Methods
import com.cesar.data.remote.model.toListLocalRecipe
import com.cesar.data.remote.model.toListRecipe
import com.cesar.domain.core.Result
import com.cesar.domain.model.Recipe
import com.cesar.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListRepositoryImp @Inject constructor(private val methods: Methods, private val recipeDao: RecipeDao) : ListRepository {
    override suspend fun getRecipes(): Flow<Result<List<Recipe>>> = flow {
       val result = methods.getRecipes()
        if (result==null){
            emit(Result.Error("error"))
        }else{
            recipeDao.insert(result.data.toListLocalRecipe())
            emit(Result.Successfull(result.data.toListRecipe()))
        }
    }

    override suspend fun getSearch(search:String): Flow<Result<List<Recipe>>> = flow {
       emit(Result.Successfull(recipeDao.getSearchRecipes(search).toListRecipe()))
    }

    override suspend fun getListPlate(): Flow<Result<List<Recipe>>> = flow {
        recipeDao.getPlates().collect{
            emit(Result.Successfull(it.toListRecipe()))
        }

    }

    override suspend fun getListDessert(): Flow<Result<List<Recipe>>> = flow {
        recipeDao.getDesserts().collect{
            emit(Result.Successfull(it.toListRecipe()))
        }
    }

    override suspend fun getFavorites(): Flow<Result<List<Recipe>>> = flow {
        recipeDao.getFavorites().collect{
            emit(Result.Successfull(it.toListRecipe()))
        }
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.update(recipe.toLocalRecipe())
    }

}