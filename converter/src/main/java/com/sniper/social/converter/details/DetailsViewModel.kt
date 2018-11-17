package com.sniper.social.converter.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsViewModel(val postTitle: String, val postBody: String, val userName: String, val commentsCount: Int): Parcelable

class DetailsViewModelBuilder {
    var postTitle = ""
    var postBody = ""
    var userName = ""
    var commentsCount = -1

    fun build(): DetailsViewModel = DetailsViewModel(postTitle, postBody, userName, commentsCount)
}

inline fun createDetailsViewModel(init: DetailsViewModelBuilder.() -> Unit): DetailsViewModel {
    val builder = DetailsViewModelBuilder()
    builder.init()
    return builder.build()
}
