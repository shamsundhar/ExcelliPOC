package com.app.excelli.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieListViewModelFactory(var movieListListener: MovieListViewModel.MovieListener) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(movieListListener) as T
    }

}