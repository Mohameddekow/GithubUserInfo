package com.dekow.githubuserinfo.domain.model

data class User(
    val name: String,
    val imageUrl: String,
    val node: String,
    val id: Int,
    val html: String,
    val placeHolderId: Int? = null
)
