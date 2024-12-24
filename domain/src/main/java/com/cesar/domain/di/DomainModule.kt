package com.cesar.domain.di

import com.cesar.domain.repository.ListRepository
import com.cesar.domain.useCase.favorites.RecipeListFavoriteUseCase
import com.cesar.domain.useCase.favorites.RecipeListFavoriteUseCaseImp
import com.cesar.domain.useCase.list.RecipeListUseCase
import com.cesar.domain.useCase.list.RecipeListUseCaseImp
import com.cesar.domain.useCase.listDessert.RecipeListDessertUseCase
import com.cesar.domain.useCase.listDessert.RecipeListDessertUseCaseImp
import com.cesar.domain.useCase.listPlate.RecipeListPlateUseCase
import com.cesar.domain.useCase.listPlate.RecipeListPlateUseCaseImp
import com.cesar.domain.useCase.search.RecipeSearchUseCase
import com.cesar.domain.useCase.search.RecipeSearchUseCaseImp
import com.cesar.domain.useCase.updateRecipe.UpdateRecipeUseCase
import com.cesar.domain.useCase.updateRecipe.UpdateRecipeUseCaseImp
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

    @Provides
    @Singleton
    fun provideRecipeSearchUseCase(repository: ListRepository) : RecipeSearchUseCase {
        return RecipeSearchUseCaseImp(repository)
    }

    @Provides
    @Singleton
    fun provideRecipeListPlateUseCase(repository: ListRepository) : RecipeListPlateUseCase {
        return RecipeListPlateUseCaseImp(repository)
    }

    @Provides
    @Singleton
    fun provideRecipeListDessertUseCase(repository: ListRepository) : RecipeListDessertUseCase {
        return RecipeListDessertUseCaseImp(repository)
    }

    @Provides
    @Singleton
    fun provideRecipeListFavoriteUseCase(repository: ListRepository) : RecipeListFavoriteUseCase {
        return RecipeListFavoriteUseCaseImp(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateRecipeUseCase(repository: ListRepository) : UpdateRecipeUseCase {
        return UpdateRecipeUseCaseImp(repository)
    }
}