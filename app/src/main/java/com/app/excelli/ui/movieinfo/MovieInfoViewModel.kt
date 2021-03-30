package com.app.excelli.ui.movieinfo

import MovieInfoResponse
import android.os.Bundle
import com.app.excelli.domain.repository.MovieInfoRepository
import com.app.excelli.ui.base.BaseViewModel
import com.app.excelli.ui.common.Constants
import com.app.excelli.ui.common.Constants.Companion.API_KEY

class MovieInfoViewModel(val args: Bundle, var movieInfoListener: MovieInfoListener) :
    BaseViewModel(), MovieInfoRepository.DataListener {
    interface MovieInfoListener {
        fun updateMovieInfo(movieInfoResponse: MovieInfoResponse?)
        fun displayLoading()
        fun hideLoading()
    }

    private var movieInfoRepository: MovieInfoRepository = MovieInfoRepository(this)

    fun getMovieInfo() {
        movieInfoListener.displayLoading()
        val movieId: String? = args.getString(Constants.BUNDLE_KEY_MOVIE_ID)
        movieInfoRepository.getMovieInfo(API_KEY, movieId)
    }

    override fun onError(message: String) {movieInfoListener.hideLoading()}

    override fun onMovieInfoSuccess(movieInfoResponse: MovieInfoResponse) {
        movieInfoListener.hideLoading()
        movieInfoListener.updateMovieInfo(movieInfoResponse)
    }
}