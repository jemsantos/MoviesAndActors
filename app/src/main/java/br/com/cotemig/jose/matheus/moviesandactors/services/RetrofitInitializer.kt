package br.com.cotemig.jose.matheus.moviesandactors.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {
    private val URL = "https://api.fluo.work/v1/"

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().also { it -> it.level = HttpLoggingInterceptor.Level.BODY })
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()
        }
    }

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun serviceAccount(): ServiceAccount {
        return retrofit.create(ServiceAccount::class.java)
    }

    //Caminho da API
    private val retrofit2 = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun serviceSeries(): ServiceSeries {
        return retrofit2.create(ServiceSeries::class.java)
    }


    fun serviceMovies(): ServiceMovies {
        return retrofit2.create(ServiceMovies::class.java)
    }

    fun serviceAtores(): ServiceAtores {
        return retrofit2.create(ServiceAtores::class.java)
    }
}