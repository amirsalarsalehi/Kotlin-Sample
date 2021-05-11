package com.lorevantonio.kotlinofflinefirst.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lorevantonio.kotlinofflinefirst.app.util.Resource
import com.lorevantonio.kotlinofflinefirst.data.repo.RecipeRepository
import com.lorevantonio.kotlinofflinefirst.domain.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    val recipesLiveData = MediatorLiveData<Resource<List<Recipe>>>()
    var pageNum: Int = 1

    fun searchRecipes(q: String) {
        executeSearchRecipes(q, pageNum)
    }

    private fun executeSearchRecipes(q: String, page: Int) {
        val source = recipeRepository.searchRecipes(q, page).asLiveData()
        recipesLiveData.addSource(source) {

            recipesLiveData.value = it

            if (it !is Resource.Loading)
                recipesLiveData.removeSource(source)

        }
    }
}