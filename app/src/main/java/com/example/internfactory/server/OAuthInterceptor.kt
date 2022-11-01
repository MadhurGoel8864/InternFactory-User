package com.example.internfactory.server

import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor constructor(
    private val tokentype:String,
    private val accesstoken:String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization","$tokentype $accesstoken")
            .build()
        return chain.proceed(request)
    }
}