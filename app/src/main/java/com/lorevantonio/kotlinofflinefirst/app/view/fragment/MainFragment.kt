package com.lorevantonio.kotlinofflinefirst.app.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lorevantonio.kotlinofflinefirst.R
import com.lorevantonio.kotlinofflinefirst.app.di.base.BaseFragment
import com.lorevantonio.kotlinofflinefirst.app.util.Resource
import com.lorevantonio.kotlinofflinefirst.app.view.recycler.RecipeAdapter
import com.lorevantonio.kotlinofflinefirst.app.viewmodel.RecipeViewModel
import com.lorevantonio.kotlinofflinefirst.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : BaseFragment() {

    private val viewModel: RecipeViewModel by viewModels()
    private var binding: FragmentMainBinding? = null
    private val recipeAdapter: RecipeAdapter =
        RecipeAdapter()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun init(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.apply {
            recyclerView.apply {
                adapter = recipeAdapter
                layoutManager = linearLayoutManager
            }
        }
        viewModel.searchRecipes("a")
    }

    override fun subscribeObservable() {
        viewModel.recipesLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data ->
                        recipeAdapter.setRecipes(data.toMutableList(), pageUpdate = {
                            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                                "Items: ${data.size.toString()}"
                            viewModel.pageNum++
                        })
                    }
                }
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                    it.data?.let { data ->
                        recipeAdapter.setRecipes(data.toMutableList(), pageUpdate = {
                            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                                "Items: ${data.size.toString()}"
                            viewModel.pageNum++
                        }, showErrorMessage = {
                            Snackbar.make(requireView(), it.msg.toString(), Snackbar.LENGTH_SHORT)
                                .show()
                        })
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_refresh, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refreshList) {
            viewModel.searchRecipes("a")
        }
        return true
    }

}