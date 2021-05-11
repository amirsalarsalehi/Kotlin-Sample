package com.lorevantonio.kotlinofflinefirst.data.repo

import com.lorevantonio.kotlinofflinefirst.app.util.ApiResponse
import com.lorevantonio.kotlinofflinefirst.app.util.Resource
import com.lorevantonio.kotlinofflinefirst.app.util.netWorkBoundResource
import com.lorevantonio.kotlinofflinefirst.data.api.RecipeApi
import com.lorevantonio.kotlinofflinefirst.data.local.RecipeDao
import com.lorevantonio.kotlinofflinefirst.domain.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeDao: RecipeDao
) {

    fun searchRecipes(q: String, page: Int): Flow<Resource<List<Recipe>>> {
        return netWorkBoundResource(
            query = {
                recipeDao.searchRecipes(q, page)
            }, fetch = {
                ApiResponse<List<Recipe>>().create(recipeApi.searchRecipes(q, page))
            }, saveFetchResult = {
                recipeDao.insertRecipe(it.recipes)
            }
        )
    }

}