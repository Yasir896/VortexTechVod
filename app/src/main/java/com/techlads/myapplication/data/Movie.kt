package com.techlads.myapplication.data


/**
 * Created by Yasir on 11/22/2020.
 */

data class Movie(
    override var imageUrl: String,
    override var streamUrl: String,
    var details: Details?
) : GenericMedia(details?.title, imageUrl, streamUrl)

data class Details(val title: String, val description: String, val year: String)