package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.OfficialGenres
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceGenres {
    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") api_key: String) : Call<OfficialGenres>
}