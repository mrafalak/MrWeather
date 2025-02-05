package com.mrapps.mrweather.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requiresAuth = request.tag(Invocation::class.java)
            ?.method()
            ?.getAnnotation(RequiresAuth::class.java) != null

        val newRequest = if (requiresAuth) {
            val requestUrl = request.url
            val newUrl = requestUrl.newBuilder()
                .addQueryParameter("apikey", apiKey)
                .build()

            request.newBuilder()
                .url(newUrl)
                .build()
        } else {
            request
        }

        return chain.proceed(newRequest)
    }
}
