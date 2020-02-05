package com.ore.internetreader

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json      = Json(JsonConfiguration.Stable)
// val appleFeed = json.parse(AppleFeed.serializer(), jsonString)


import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.internal.*

@Serializable
data class AppleFeed(
    val feed: Feed
)

@Serializable
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

@Serializable
data class Author(
    val name: String,
    val uri: String
)

@Serializable
data class Link(
    val self: String? = null,
    val alternate: String? = null
)

@Serializable
data class Result(
    val artistName: String,
    val id: String,
    val releaseDate: String,
    val name: String,
    val kind: Kind,

    @SerialName("artistId")
    val artistID: String,

    @SerialName("artistUrl")
    val artistURL: String,

    val artworkUrl100: String,
    val genres: List<Genre>,
    val url: String
)

@Serializable
data class Genre(
    @SerialName("genreId")
    val genreID: String,

    val name: String,
    val url: String
)

@Serializable(with = Kind.Companion::class)
enum class Kind(val value: String) {
    EpubBook("epubBook"),
    Ibook("ibook");

    companion object : KSerializer<Kind> {
        override val descriptor: SerialDescriptor
            get() {
                return StringDescriptor
            }

        override fun deserialize(decoder: Decoder): Kind = when (decoder.decodeString()) {
            "epubBook" -> EpubBook
            "ibook" -> Ibook
            else -> throw IllegalArgumentException()
        }

        override fun serialize(encoder: Encoder, obj: Kind) {
            return encoder.encodeString(obj.value)
        }
    }
}
