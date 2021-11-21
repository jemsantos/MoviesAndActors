package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.AvailableRegions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceRegion {
    @GET("watch/providers/regions")
    fun getRegions(@Query("api_key") api_key: String) : Call<AvailableRegions>
}