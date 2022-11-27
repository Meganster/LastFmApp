package com.appsfactory.testtask.domain.model

data class Album(
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
) {
    val name: String
        get() = "$title $firstName $lastName"
}