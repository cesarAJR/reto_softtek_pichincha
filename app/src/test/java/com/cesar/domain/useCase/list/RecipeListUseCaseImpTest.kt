package com.cesar.domain.useCase.list

import com.cesar.data.repository.ListRepositoryImp
import com.cesar.domain.core.Result
import com.cesar.domain.core.Result.Successfull
import com.cesar.domain.core.Result.Error
import com.cesar.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeListUseCaseImpTest{

    @Mock
    lateinit var repository : ListRepositoryImp

    @Test
    fun getList_success()= runTest{
            val list = listOf(
                Recipe(
                    "receta1",
                    "image1",
                    "description1",
                    true,
                        "1",
                    listOf(),
                    listOf()
                    ),
                Recipe(
                    "receta2",
                    "image2",
                    "description2",
                    true,
                    "1",
                    listOf(),
                    listOf()
                )
            )

            val flow = flow {
                emit(Successfull(list))
            }

            `when`(repository.getRecipes()).thenReturn(flow)

            val useCase  = RecipeListUseCaseImp(repository)
            val response = useCase.execute()

            response.collect{
                assertEquals(list, it.data)
            }
    }

    @Test
    fun getList_error() = runTest{
            val list = listOf<Recipe>()

            val flow = flow {
                emit(Error("error", list))
            }

            `when`(repository.getRecipes()).thenReturn(flow)

            val useCase  = RecipeListUseCaseImp(repository)
            val response = useCase.execute()
            response.collect{
                assertEquals(true, it.message?.isNotEmpty())
            }
    }

}