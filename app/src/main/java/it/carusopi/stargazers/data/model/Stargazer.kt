package it.carusopi.stargazers.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by carusopi on 27/10/2017.
 */

data class Stargazer(
        @SerializedName("login") val login: String? = null,
        @SerializedName("id") val id: Int? = null,
        @SerializedName("avatar_url") val avatarUrl: String? = null)

