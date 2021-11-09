package br.com.cotemig.jose.matheus.moviesandactors.models

import java.io.Serializable

class Account: Serializable {
    var id: String? = null
    var email: String? = null
    var password: String? = null
    var name: String? = null
    var token: String? = null
}