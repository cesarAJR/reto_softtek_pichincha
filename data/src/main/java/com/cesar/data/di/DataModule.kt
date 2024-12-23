package com.cesar.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.cesar.data.core.BasicInterceptor
import com.cesar.data.database.AppDatabase
import com.cesar.data.database.dao.RecipeDao
import com.cesar.data.remote.Methods
import com.cesar.data.repository.ListRepositoryImp
import com.cesar.domain.repository.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideListRepository(methods: Methods,recipeDao: RecipeDao) : ListRepository{
        return ListRepositoryImp(methods,recipeDao)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences(
           "AppPreferences", Context.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun provideRecipeDao(appDatabase: AppDatabase) : RecipeDao = appDatabase.RecipeDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "dbRecipe"
    ).build()

    @Provides
    @Singleton
    fun provideBasicInterceptor() : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(BasicInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Methods {
        return Retrofit.Builder()
            .baseUrl("https://1b53e132-5111-4efc-b168-ed1ca2b30387.mock.pstmn.io/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }

}