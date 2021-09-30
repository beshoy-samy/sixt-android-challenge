package com.sixt.core.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import coil.load
import com.sixt.core.R

fun Context.shortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.longToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun ImageView.loadImage(
    url: String?,
    crossfade: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.ic_image_placeholder
) = load(url) {
    crossfade(crossfade)
    placeholder(placeholder)
    error(placeholder)
}