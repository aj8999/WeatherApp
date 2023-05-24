package com.wheatherapp

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.wheatherapp.databinding.ActivityHomeScreenBinding


class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}