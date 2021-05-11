package com.lorevantonio.kotlinofflinefirst.data.api

import com.lorevantonio.kotlinofflinefirst.domain.Recipe
import com.lorevantonio.kotlinofflinefirst.domain.RecipesSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("/api/search")
    suspend fun searchRecipes(
        @Query("q") q: String,
        @Query("page") page: Int
    ): Response<RecipesSearch>


}