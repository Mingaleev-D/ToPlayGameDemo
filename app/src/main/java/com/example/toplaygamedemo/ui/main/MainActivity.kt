package com.example.toplaygamedemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.toplaygamedemo.R
import com.example.toplaygamedemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      val navHostFragment =
         supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
      var navController = navHostFragment.navController
      setupWithNavController(binding.btnNavView, navController)
   }
}