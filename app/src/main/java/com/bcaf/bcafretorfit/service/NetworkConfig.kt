package com.bcaf.bcafretorfit.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkConfig {

    fun getInterceptor() : OkHttpClient{
        var logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        return okHttpClient
    }

    fun getInterceptorWithHeader() : OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                    chain ->
                val request = chain.request().newBuilder()
                    .addHeader("app-id", "63bcecb03cea2a2870e0291b")
                chain.proceed(request.build())
            }
            .build()
        return okHttpClient
    }

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitPostDummy() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dummyapi.io/data/v1/")
            .client(getInterceptorWithHeader())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getServiceOMDB() = getRetrofit().create(OMDBApiInterface::class.java)
    fun getServiceDummy() = getRetrofitPostDummy().create(DummyAPIInterface::class.java)
}