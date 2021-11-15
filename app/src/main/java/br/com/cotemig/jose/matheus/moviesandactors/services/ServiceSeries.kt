package br.com.cotemig.jose.matheus.moviesandactors.services


import br.com.cotemig.jose.matheus.moviesandactors.models.ListSeries
import retrofit2.Call
import retrofit2.http.*

interface ServiceSeries {
    @GET("tv/popular")
    fun getseries(@Query("api_key")api_key:String) : Call<ListSeries>
}