package com.example.openpayprueba.core.core.di

import android.app.Application
import android.content.Context
import com.example.openpayprueba.BaseApplication
import com.example.openpayprueba.BuildConfig
import com.example.openpayprueba.core.core.network.LocalChuckerInterceptor
import com.example.openpayprueba.core.core.network.StampsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideStampsApi(retrofit: Retrofit): StampsApi {
        return retrofit.create(StampsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val url = originalRequest.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val requestBuilder = originalRequest.newBuilder().url(url)
            val request = requestBuilder.build()

            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(apiKeyInterceptor: Interceptor, ctx: Context): OkHttpClient {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(apiKeyInterceptor)
        if (BuildConfig.DEBUG) httpClient.addInterceptor(LocalChuckerInterceptor().getChucker(ctx))
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideBaseApplication(@ApplicationContext appContext: Context): Context {
        return appContext
    }
}