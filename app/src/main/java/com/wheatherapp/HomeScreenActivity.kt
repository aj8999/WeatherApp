package com.wheatherapp


import android.accessibilityservice.GestureDescription
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.wheatherapp.databinding.ActivityHomeScreenBinding


class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadFragment(FragmentCity.newInstance())
        binding.navView.setOnItemSelectedListener { item ->
            var fragment: Fragment
            when (item.itemId) {
                R.id.navHome -> {
                    fragment = FragmentCity()
                    loadFragment(fragment)
                    true
                }
                R.id.navSettings -> {
                    fragment = FragmentSettings()
                    loadFragment(fragment)
                    true
                }

                else -> false
            }

        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.rlContainer, fragment)
            .commit()
    }
}