package com.example.currencyapp.api

import com.example.currencyapp.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val interceptor = Interceptor { chain ->
        val request = chain.request()
        val newRequest = request.newBuilder()
            .header("apikey", BuildConfig.API_KEY)
            .method(request.method, request.body)
            .build()
        chain.proceed(newRequest)
    }

    private val okHttpClient = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)

    @Singleton
    @Provides
    fun createApiRetrofit(): Retrofit {
        okHttpClient.apply {
            addInterceptor(OkHttpProfilerInterceptor())
            addInterceptor(interceptor)
            addInterceptor(HttpLoggingInterceptor())
        }
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideOperationService(retrofit: Retrofit): Api = retrofit.create()
}


