package com.app.excelli.retrofit

import MovieInfoResponse
import MovieListResponse
import com.app.excelli.ui.common.Constants.Companion.QUERY_KEY_PARAM_API_KEY
import com.app.excelli.ui.common.Constants.Companion.QUERY_KEY_PARAM_I
import com.app.excelli.ui.common.Constants.Companion.QUERY_KEY_PARAM_S
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun searchMovies(
        @Query(QUERY_KEY_PARAM_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PARAM_S) s: String,
    ): Observable<MovieListResponse>

    @GET(".")
    fun getMovieInfoByID(
        @Query(QUERY_KEY_PARAM_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PARAM_I) i: String,
    ): Observable<MovieInfoResponse>

}