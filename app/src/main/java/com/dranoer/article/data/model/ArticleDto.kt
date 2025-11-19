package com.dranoer.article.data.model

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String?,

    @SerializedName("summary")
    val summary: String?,

    @SerializedName("updatedAt")
    val updatedAt: String?,

    @SerializedName("contentMarkdown")
    val contentMarkdown: String? = null
)