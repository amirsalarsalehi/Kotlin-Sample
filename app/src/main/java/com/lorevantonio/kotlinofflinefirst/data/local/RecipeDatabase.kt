package com.lorevantonio.kotlinofflinefirst.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lorevantonio.kotlinofflinefirst.app.util.AppKeys
import com.lorevantonio.kotlinofflinefirst.domain.Recipe

@Database(entities = [Recipe::class], version = AppKeys.DB_VER, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

}