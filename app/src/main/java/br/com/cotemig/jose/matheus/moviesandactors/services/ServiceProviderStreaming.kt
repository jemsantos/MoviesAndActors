package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.Watch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceProviderStreaming {
    @GET("watch/providers/movie?&watch_region=BR")
    fun getProvidersStreaming(@Query("api_key") api_key: String) : Call<Watch>

    @GET("watch/providers/movie")
    fun getProvidersStreamingByRegion(@Query("watch_region") watch_region: String, @Query("api_key") api_key: String) : Call<Watch>
}