package com.appsfactory.testtask.domain.model

import java.io.Serializable

data class Album(
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
) : Serializable {
    val name: String
        get() = "$title $firstName $lastName"
}