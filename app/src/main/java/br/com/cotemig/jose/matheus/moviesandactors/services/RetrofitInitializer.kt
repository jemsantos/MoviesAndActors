package br.com.cotemig.jose.matheus.moviesandactors.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val URL = "https://api.fluo.work/v1/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun serviceAccount(): ServiceAccount {
        return retrofit.create(ServiceAccount::class.java)
    }
}