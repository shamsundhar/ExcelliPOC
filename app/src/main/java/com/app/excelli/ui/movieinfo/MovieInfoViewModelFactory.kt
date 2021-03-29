package com.app.excelli.ui.movieinfo

import android.os.Bundle
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider


class MovieInfoViewModelFactory(
    private val args: Bundle,
    var movieInfoListener: MovieInfoViewModel.MovieInfoListener
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieInfoViewModel(args, movieInfoListener) as T
    }
}