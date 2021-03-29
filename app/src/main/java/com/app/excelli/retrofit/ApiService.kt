package com.app.excelli.retrofit

import MovieInfoResponse
import MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun searchMovies(
        @Query("apikey") apikey: String,
        @Query("s") s: String,
    ): Observable<MovieListResponse>

    @GET(".")
    fun getMovieInfoByID(
        @Query("apikey") apikey: String,
        @Query("i") i: String,
    ): Observable<MovieInfoResponse>

}