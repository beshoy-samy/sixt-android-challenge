package com.sixt.cars.presentation

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MapStyleOptions

fun GoogleMap.setMapStyle(context: Context, @RawRes mapStyle: Int) {
    try {
        val resource = MapStyleOptions.loadRawResourceStyle(context, mapStyle)
        val success = this.setMapStyle(resource)
        if (success.not() and BuildConfig.DEBUG) Log.e("GoogleMap", "error setting map style")
    } catch (e: Resources.NotFoundException) {
        if (BuildConfig.DEBUG) Log.e("GoogleMap", "error setting map style")
    }
}

fun Context.bitmapDescriptorFromVector(
    @DrawableRes vectorRes: Int,
    @ColorRes colorRes: Int? = null
): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(this, vectorRes)
    vectorDrawable!!.setBounds(
        0,
        0,
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight
    )
    colorRes?.let {
        DrawableCompat.setTint(vectorDrawable, ContextCompat.getColor(this, it))
    }
    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}