package br.com.cotemig.jose.matheus.moviesandactors.services

import br.com.cotemig.jose.matheus.moviesandactors.models.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface ServiceAccount {
    @POST("account/auth")
    fun auth(@Body account: Account): Call<Account>

    @POST("account")
    fun register(@Body account: Account): Call<Account>

    @PUT("account")
    fun update(@Body account: Account): Call<Account>

    @POST("account/forgot")
    fun forgot(@Body acount: Account): Call<Void>
}