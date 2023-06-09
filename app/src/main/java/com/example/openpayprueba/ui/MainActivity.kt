package com.example.openpayprueba.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.openpayprueba.R
import com.example.openpayprueba.databinding.ActivityMainBinding
import com.example.openpayprueba.ui.Map.MapFragment
import com.example.openpayprueba.ui.Movies.MoviesFragment
import com.example.openpayprueba.ui.Profile.fragment.ProfileFragment
import com.example.openpayprueba.ui.Upload.UploadFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ProfileFragment())

        binding.bnvMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_profile -> replaceFragment(ProfileFragment())
                R.id.navigation_movies -> replaceFragment(MoviesFragment())
                R.id.navigation_map -> replaceFragment(MapFragment())
                R.id.navigation_upload -> replaceFragment(UploadFragment())
                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flMain, fragment)
        fragmentTransaction.commit()
    }
}