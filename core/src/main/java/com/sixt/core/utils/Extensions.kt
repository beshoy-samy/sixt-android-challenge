package com.sixt.core.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import coil.load
import com.sixt.core.R
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

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

fun Throwable.networkErrorMessage(context: Context): String? =
    when (this) {
        is IOException, is ConnectException, is SocketTimeoutException -> context.getString(R.string.network_error)
        is HttpException -> {
            when (code()) {
                HttpURLConnection.HTTP_INTERNAL_ERROR -> context.getString(R.string.internal_server_error)
                HttpURLConnection.HTTP_NOT_FOUND -> context.getString(R.string.content_not_found)
                else -> context.getString(R.string.general_network_error_message)
            }
        }
        else -> null
    }