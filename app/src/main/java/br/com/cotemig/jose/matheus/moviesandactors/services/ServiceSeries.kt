package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.*
import retrofit2.Call
import retrofit2.http.*

interface ServiceSeries {
    @GET("tv/popular")
    fun getseries(@Query("api_key") api_key: String) : Call<ListSeries>

    @GET("tv/{id}")
    fun getseriesseason(@Path("id") id: Int,
        @Query("api_key") api_key: String) : Call<ListSeasons>


    @GET("tv/{id}/season/{season}")
    fun getseriesepisode(@Path("id") id: Int,
                        @Path("season") season: Int,
                        @Query("api_key") api_key: String) : Call<ListEpisodes>


}