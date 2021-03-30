package com.app.excelli.ui.movielist

import MovieListResponse
import com.app.excelli.domain.repository.MovieListRepository
import com.app.excelli.ui.base.BaseViewModel

class MovieListViewModel(private var movieListener: MovieListener) : BaseViewModel(),
    MovieListRepository.DataListener {
    interface MovieListener {
        fun updateMoviesList(movieListResponse: MovieListResponse)
        fun displayLoading()
        fun hideLoading()
    }

    private var movieListRepository: MovieListRepository = MovieListRepository(this)

    fun loadMoviesList(apiKey: String, searchString: String) {
        movieListener.displayLoading()
        movieListRepository.getMovies(apiKey, searchString)
    }

    override fun onError(message: String) {
        movieListener.hideLoading()
    }

    override fun onSuccess(movieListResponse: MovieListResponse) {
        movieListener.hideLoading()
        movieListener.updateMoviesList(movieListResponse)
    }

}