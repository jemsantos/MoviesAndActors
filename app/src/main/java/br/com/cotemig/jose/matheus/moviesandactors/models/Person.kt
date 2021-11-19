package br.com.cotemig.jose.matheus.moviesandactors.models

/* https://api.themoviedb.org/3/person/popular?api_key=ec0d4e364d9d4899a085d61c47e589d3 */
class Person {
    var page: Int? = null
    var total_pages: Int? = null
    var results: List<Atores>? = null
}