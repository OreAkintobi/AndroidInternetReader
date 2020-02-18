package com.ore.internetreader

data class AppleFeed(
    val feed: Feed
)

data class Feed(
    val title: String,
    val id: String,
    val author: Author,
    val links: List<Link>,
    val copyright: String,
    val country: String,
    val icon: String,
    val updated: String,
    val results: List<Result>
)

data class Author(
    val name: String,
    val uri: String
)

data class Link(
    val self: String? = null,
    val alternate: String? = null
)

data class Result(
    val artistName: String,
    val id: String,
    val releaseDate: String,
    val name: String,
    val artistID: String,
    val artistURL: String,
    val artworkUrl100: String,
    val genres: List<Genre>,
    val url: String
)

data class Genre(
    val genreID: String,
    val name: String,
    val url: String
)
