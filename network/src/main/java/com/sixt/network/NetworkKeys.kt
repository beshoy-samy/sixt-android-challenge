package com.sixt.network

object NetworkKeys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun getBaseUrl(): String

}