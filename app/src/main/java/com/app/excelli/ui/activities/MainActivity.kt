package com.app.excelli.ui.activities


import MovieListFragment
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.app.excelli.R
import com.app.excelli.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val TAG = "MovieListFragment"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        displayMoviesListFragment()
    }

    private fun displayMoviesListFragment() {
        val topRatedMoviesFragment =
            MovieListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, topRatedMoviesFragment, TAG)
            .commit()
    }

}