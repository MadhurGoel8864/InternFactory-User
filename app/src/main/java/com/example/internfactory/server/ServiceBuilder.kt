package com.example.internfactory.server
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

object ServiceBuilder {
    private const val URL = " https://99a1-223-233-74-91.in.ngrok.io"

//    private val okHttp = OkHttpClient.Builder()
//
//    private val builder = Retrofit.Builder().baseUrl(URL)
//        .addConverterFactory(ScalarsConverterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(okHttp.build())
//
//    private val retrofit = builder.build()

    fun providesApiService(okHttpClient: OkHttpClient):RetrofitApi =Retrofit
        .Builder()
        .run {
            baseUrl(URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
            build()
        }.create(RetrofitApi::class.java)

    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(OAuthInterceptor("Bearer",
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbnRlcm5mYWN0b3J5QGdtYWlsLmNvbSIsImV4cCI6MTY2NzI0MTU3MywiaWF0IjoxNjY3MjM5NzczfQ.eot-eadKl5g_aro7Gso2EN1FrCSvhzTxsHpTVFATQ9yLTn4wmkW4OSrbQ-3LrMpNmCNPsiq83GTmrACxuPWhxA"
        )
        ).addInterceptor(interceptor)
            .build()
    }

//    fun <T> buildService(serviceType : Class<T>) : T{
//        return retrofit.create(serviceType)
//    }
}
