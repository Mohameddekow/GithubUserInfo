package com.dekow.githubuserinfo.domain.model

import com.google.gson.annotations.SerializedName

data class UserDetails (
    val avatarUrl: String? = null,
    val bio: String? = null,
    val blog: String? = null,
    val company: String? = null,
    val htmlUrl: String? = null,
    val followers: Int? = null,
    val followersUrl: String? = null,
    val following: Int? = null,
    val followingUrl: String? = null,
    val id: Int? = null,
    val location: String? = null,
    val login: String? = null,
    val name: String? = null,
    val organizationsUrl: String? = null,
    val publicRepos: Int? = null,
    val receivedEventsUrl: String? = null,
    val reposUrl: String? = null,
    val starredUrl: String? = null,
    val twitterUsername: String? = null,
    val url: String? = null,
    val placeHolderId : Int? = null,
    val publicGist : Int? = null,
    val nodeId : String? = null
)
