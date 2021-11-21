package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAtores {
    @GET("person/popular")
    fun getatores(@Query("api_key") api_key: String) : Call<ListAtores>

    @GET("person/{id}/movie_credits")
    fun getatoresmovies(@Path("id") id: Int,
        @Query("api_key") api_key: String) : Call<ListAtoresMovies>
}