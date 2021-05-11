package com.lorevantonio.kotlinofflinefirst.domain

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_recipe")
data class Recipe(
    @PrimaryKey
    @NonNull
    @SerializedName("recipe_id")
    @Expose
    val recipeId: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("social_rank")
    @Expose
    val socialRank: Double,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String
)