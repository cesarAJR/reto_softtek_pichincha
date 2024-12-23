package com.cesar.data.remote

import com.cesar.data.remote.model.DataResponse
import retrofit2.http.GET

interface Methods {

    @GET("getRecipes")
    suspend fun getRecipes(): DataResponse?
}