package br.com.cotemig.jose.matheus.moviesandactors.models

class Atores {
    var id: Int = 0
    var name: String = ""
    var profile_path: String = ""            // "/9ojwd1UhOJx6Jx8s6hLwNP2HwGb.jpg"

    var known_for_department: String? = null // "Acting"
    var known_for: List<Movie>? = null       // filmes...
}