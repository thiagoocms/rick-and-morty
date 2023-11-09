package com.example.desafio.model

import java.io.Serializable

data class Person(
    var id: Number,
    var name: String,
    var status: String,
    var species: String,
    var gender: String,
    var image: String,
    var created: String
) : Serializable

data class Info(
    var count: Number,
    var pages: Number,
    var next: String,
    var prev: String
)

data class Responce(
    var info: Info,
    var results: ArrayList<Person>
)
