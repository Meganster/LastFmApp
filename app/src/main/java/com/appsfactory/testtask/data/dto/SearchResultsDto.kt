package com.appsfactory.testtask.data.dto

import com.google.gson.annotations.SerializedName

data class SearchResultsDto(
    @SerializedName("opensearch:itemsPerPage")
    val opensearchItemsPerPage: String?,
    @SerializedName("artistmatches")
    val artistMatches: ArtistMatchesDto?,
    @SerializedName("opensearch:startIndex")
    val opensearchStartIndex: String?,
    @SerializedName("opensearch:totalResults")
    val opensearchTotalResults: String?,
    @SerializedName("@attr")
    val attr: AttrDto
)