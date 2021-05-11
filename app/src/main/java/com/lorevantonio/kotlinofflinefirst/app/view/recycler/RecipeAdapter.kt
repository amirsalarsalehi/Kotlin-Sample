package com.lorevantonio.kotlinofflinefirst.app.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.lorevantonio.kotlinofflinefirst.app.view.recycler.RecipeAdapter.RecipeViewHolder
import com.lorevantonio.kotlinofflinefirst.databinding.RecipeItemBinding
import com.lorevantonio.kotlinofflinefirst.domain.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeAdapter : RecyclerView.Adapter<RecipeViewHolder>() {

    var recipes = mutableListOf<Recipe>()

    fun setRecipes(
        value: MutableList<Recipe>,
        pageUpdate: () -> Unit = {},
        showErrorMessage: () -> Unit = {}
    ) {
        CoroutineScope(Dispatchers.IO).run {
            if (value != recipes) {
                recipes.clear()
                recipes.addAll(value)
                pageUpdate()
                notifyDataSetChanged()
            } else
                showErrorMessage()
        }
    }

    class RecipeViewHolder(private val binding: RecipeItemBinding) : ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.apply {
                Glide.with(itemView)
                    .load(recipe.imageUrl)
                    .into(imageView)
                textViewTitle.text = recipe.title
                textViewRank.text = recipe.socialRank.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context))
        return RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentItem = recipes[position]
        if (currentItem != null)
            holder.bind(currentItem)
    }
}