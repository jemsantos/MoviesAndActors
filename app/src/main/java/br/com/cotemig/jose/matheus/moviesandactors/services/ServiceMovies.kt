package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.ListMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceMovies {
    @GET("movie/popular")
    fun getmovies(@Query("api_key")api_key:String) : Call<ListMovies>

}