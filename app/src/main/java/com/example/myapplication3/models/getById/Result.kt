package com.example.myapplication.models.getById

data class Result(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val resourceURI: String,
    val comics: Comics,
    val series: Series,
    val stories: Stories,
    val events: Events,
    val urls: List<Url>,
)