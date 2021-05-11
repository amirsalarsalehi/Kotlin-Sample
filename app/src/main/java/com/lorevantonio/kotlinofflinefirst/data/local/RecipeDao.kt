package com.lorevantonio.kotlinofflinefirst.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lorevantonio.kotlinofflinefirst.domain.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM tbl_recipe WHERE title LIKE '%' || :q || '%' ORDER BY socialRank DESC LIMIT (:page * 30)")
    fun searchRecipes(q: String, page: Int): Flow<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipes: List<Recipe>)

}