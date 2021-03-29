package com.app.excelli.domain.repository

import MovieInfoResponse
import com.app.excelli.retrofit.ApiService
import com.app.excelli.retrofit.ServiceFactory
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MovieInfoRepository(var dataListener: DataListener) {

    interface DataListener {
        fun onError(message: String)
        fun onMovieInfoSuccess(movieInfoResponse: MovieInfoResponse)
    }

    private var factory: ServiceFactory = ServiceFactory()
    private var api: ApiService = factory.provideApi()


    fun getMovieInfo(apiKey: String, imdbId: String?): Observer<MovieInfoResponse> {
        return api.getMovieInfoByID(apiKey, imdbId!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(MyObserver())
    }

    private inner class MyObserver : Observer<MovieInfoResponse> {
        override fun onComplete() {}
        override fun onSubscribe(d: Disposable) {}
        override fun onNext(movieInfoResponse: MovieInfoResponse) {
            dataListener.onMovieInfoSuccess(movieInfoResponse)
        }

        override fun onError(e: Throwable) {}
    }

}