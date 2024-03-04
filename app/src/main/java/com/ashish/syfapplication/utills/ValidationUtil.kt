package com.ashish.syfapplication.utills

import com.ashish.syfapplication.model.ImageResponse

object ValidationUtil {

    fun validateImage(image: ImageResponse) : Boolean {
        return image.url!!.isNotEmpty() && image.id!!.isNotEmpty()
    }
}