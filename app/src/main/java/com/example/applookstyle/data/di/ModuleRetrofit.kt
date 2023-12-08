package com.example.cronodepro.data.di

import com.example.cronodepro.core.Constant
import com.example.cronodepro.data.services.LookStyleServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleRetrofit {
    @Provides
    @Singleton
    fun retrofitProvider(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor {
                val request = it.request().newBuilder()
                    .header("Authorization", "bearer ${Constant.AUTHO_JWT}")
                    .build()
                it.proceed(request)
            }
        }.build()
    }

    @Provides
    @Singleton
    fun provideServices(retrofit: Retrofit): LookStyleServices {
        return retrofit.create(LookStyleServices::class.java)
    }


}