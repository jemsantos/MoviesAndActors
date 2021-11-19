package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicePersonPopular {
    @GET("person/popular")
    fun getPersonPopular(@Query("api_key") api_key: String) : Call<Person>
}