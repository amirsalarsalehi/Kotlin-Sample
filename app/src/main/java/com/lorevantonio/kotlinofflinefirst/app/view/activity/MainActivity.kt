package com.lorevantonio.kotlinofflinefirst.app.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.lorevantonio.kotlinofflinefirst.R
import com.lorevantonio.kotlinofflinefirst.app.util.Resource
import com.lorevantonio.kotlinofflinefirst.app.viewmodel.RecipeViewModel
import com.lorevantonio.kotlinofflinefirst.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityMainToolbar.mainToolbar)
    }
}