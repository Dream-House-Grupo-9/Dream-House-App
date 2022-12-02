package com.dreamhouse.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ImgurUploadJson(
    val data: Data?,
    val status: Int?,
    val success: Boolean?
) : Parcelable {
    fun getImageLink(): String? {
        return data?.link
    }
}

@Parcelize
data class Data(
    val account_id: Int,
    val account_url: String,
    val ad_type: @RawValue Any,
    val ad_url: @RawValue Any,
    val animated: Boolean,
    val bandwidth: Int,
    val datetime: Int,
    val deletehash: String,
    val description: @RawValue Any,
    val favorite: Boolean,
    val has_sound: Boolean,
    val height: Int,
    val hls: String,
    val id: String,
    val in_gallery: Boolean,
    val in_most_viral: Boolean,
    val is_ad: Boolean,
    val link: String,
    val mp4: String,
    val name: String,
    val nsfw: @RawValue Any,
    val section: @RawValue Any,
    val size: Int,
    val tags: @RawValue List<Any>,
    val title: @RawValue Any,
    val type: String,
    val views: Int,
    val vote: @RawValue Any,
    val width: Int
) : Parcelable
