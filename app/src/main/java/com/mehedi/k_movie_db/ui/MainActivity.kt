package com.mehedi.k_movie_db.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.k_movie_db.R
import com.example.k_movie_db.databinding.ActivityMainBinding
import com.mehedi.k_movie_db.utils.bottomNavSetKoro
import com.mehedi.k_movie_db.utils.navControllerDe
import com.mehedi.k_movie_db.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toast(this, "kisu akta")

        navController = navControllerDe(R.id.fragmentContainerView)
        setupWithNavController(binding.bottomNaView, navController)

        // binding.bottomNaView.setupWithNavController(navController)
        binding.bottomNaView.bottomNavSetKoro(navController)
    }
}
