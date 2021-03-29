package com.app.excelli.ui.movielist

import MovieListResponse
import com.app.excelli.domain.repository.MovieListRepository
import com.app.excelli.ui.base.BaseViewModel

class MovieListViewModel(var movieListener: MovieListener) : BaseViewModel(),
    MovieListRepository.DataListener {
    interface MovieListener {
        fun updateMoviesList(movieListResponse: MovieListResponse)
    }

    private var movieListRepository: MovieListRepository = MovieListRepository(this)

    fun loadMoviesList(apiKey: String, searchString: String) {
        movieListRepository.getMovies(apiKey, searchString)
    }

    override fun onError(message: String) {}

    override fun onSuccess(movieListResponse: MovieListResponse) {
        movieListener.updateMoviesList(movieListResponse)
    }

}