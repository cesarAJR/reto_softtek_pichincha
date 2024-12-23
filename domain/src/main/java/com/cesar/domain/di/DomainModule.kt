package com.cesar.domain.di

import com.cesar.domain.repository.ListRepository
import com.cesar.domain.useCase.list.RecipeListUseCase
import com.cesar.domain.useCase.list.RecipeListUseCaseImp
import dagger.Component.Factory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideRecipeListUseCase(repository: ListRepository) : RecipeListUseCase {
        return RecipeListUseCaseImp(repository)
    }
}