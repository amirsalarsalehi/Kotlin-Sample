package com.lorevantonio.kotlinofflinefirst.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipesSearch(
    @SerializedName("count")
    @Expose
    val count: Int,

    @SerializedName("recipes")
    @Expose
    val recipes: List<Recipe>
)