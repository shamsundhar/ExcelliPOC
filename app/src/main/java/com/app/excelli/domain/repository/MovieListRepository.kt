package com.app.excelli.domain.repository

import MovieListResponse
import com.app.excelli.retrofit.ApiService
import com.app.excelli.retrofit.ServiceFactory
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MovieListRepository(var dataListener: DataListener) {

    interface DataListener {
        fun onError(message: String)
        fun onSuccess(movieListResponse: MovieListResponse)
    }

    private var factory: ServiceFactory = ServiceFactory()
    private var api: ApiService = factory.provideApi()
    fun getMovies(apiKey: String, searchString: String): Observer<MovieListResponse> {
        return api.searchMovies(apiKey, searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(MyObserver())
    }

    private inner class MyObserver : Observer<MovieListResponse> {
        override fun onComplete() {

        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(movieListResponse: MovieListResponse) {
            dataListener.onSuccess(movieListResponse)
        }

        override fun onError(e: Throwable) {

        }


    }
}