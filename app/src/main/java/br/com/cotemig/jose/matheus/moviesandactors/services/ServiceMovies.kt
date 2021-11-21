package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.ListCast
import br.com.cotemig.jose.matheus.moviesandactors.models.ListMovies
import br.com.cotemig.jose.matheus.moviesandactors.models.MovieInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceMovies {
    @GET("movie/popular")
    fun getmovies(@Query("api_key") api_key: String) : Call<ListMovies>

    @GET("movie/{id}")
    fun getmoviesinfo(@Path("id") id: Int,
                      @Query("api_key") api_key: String) : Call<MovieInfo>

    @GET("movie/{id}/credits")
    fun getcast(@Path("id") id: Int,
                @Query("api_key") api_key: String) : Call<ListCast>
}