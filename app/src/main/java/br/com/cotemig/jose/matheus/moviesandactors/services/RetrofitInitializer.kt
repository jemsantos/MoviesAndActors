package br.com.cotemig.jose.matheus.moviesandactors.services


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    /* servico de autenticação */
    private val URL = "https://api.fluo.work/v1/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun serviceAccount(): ServiceAccount {
        return retrofit.create(ServiceAccount::class.java)
    }
    /* FIM - servico de autenticação */


    /* Caminho da API */
    private val retrofit2 = Retrofit.Builder()
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

    fun servicePersonPopular(): ServicePersonPopular {
        return retrofit2.create(ServicePersonPopular::class.java)
    }

    fun serviceProviderStreaming(): ServiceProviderStreaming {
        return retrofit2.create(ServiceProviderStreaming::class.java)
    }

    fun serviceAvailableRegion(): ServiceRegion {
        return retrofit2.create(ServiceRegion::class.java)
    }

    fun serviceOfficialGenres(): ServiceGenres {
        return retrofit2.create(ServiceGenres::class.java)
    }
    /* FIM - Caminho da API */
}