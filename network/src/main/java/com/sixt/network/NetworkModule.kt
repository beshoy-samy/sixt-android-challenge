package com.sixt.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val NETWORK_CONNECT_TIMEOUT = 30L
    private const val NETWORK_READ_TIMEOUT = 30L
    private const val NETWORK_WRITE_TIMEOUT = 30L

    @Singleton
    @Provides
    fun provideMoshi() =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun provideOkhttp() =
        OkHttpClient.Builder()
            .connectTimeout(NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG)
                    addInterceptor(
                        HttpLoggingInterceptor { message ->
                            if (BuildConfig.DEBUG) Log.i("Network", message)
                        }.apply { level = HttpLoggingInterceptor.Level.BODY }
                    )
            }
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okhttp: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(NetworkKeys.getBaseUrl())
            .client(okhttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}