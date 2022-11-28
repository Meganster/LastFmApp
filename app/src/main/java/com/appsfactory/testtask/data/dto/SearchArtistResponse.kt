package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class SearchArtistResponse(
    @SerializedName("results")
    val results: Results
)

data class Results(
    @SerializedName("opensearch:Query")
    val openSearchQuery: OpenSearchQuery,
    @SerializedName("@attr")
    val Attr: Attr,
    @SerializedName("opensearch:itemsPerPage")
    val opensearchItemsPerPage: String = "",
    @SerializedName("artistmatches")
    val artistMatches: ArtistMatches,
    @SerializedName("opensearch:startIndex")
    val opensearchStartIndex: String = "",
    @SerializedName("opensearch:totalResults")
    val opensearchTotalResults: String = ""
)

data class Attr(
    @SerializedName("artist") val artist: String,
    @SerializedName("page") val page: String,
    @SerializedName("perPage") val perPage: String,
    @SerializedName("totalPages") val totalPages: String,
    @SerializedName("total") val total: String,
    @SerializedName("rank") val rank: String,
    @SerializedName("for") val forArtist: String
)

data class OpenSearchQuery(
    @SerializedName("startPage")
    val startPage: String = "",
    @SerializedName("#text")
    val Text: String = "",
    @SerializedName("role")
    val role: String = "",
    @SerializedName("searchTerms")
    val searchTerms: String = ""
)

data class ArtistMatches(
    @SerializedName("artist")
    val artist: List<ArtistDto>?
)