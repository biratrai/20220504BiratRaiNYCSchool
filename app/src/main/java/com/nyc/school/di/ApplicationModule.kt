package com.nyc.school.di

import com.nyc.school.network.SchoolApi
import com.nyc.school.network.SchoolRepository
import com.nyc.school.network.SchoolRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/resource/")
                .client(createClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideSchoolRepository(schoolRepository: SchoolRepositoryImpl): SchoolRepository = schoolRepository

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): SchoolApi = retrofit.create(SchoolApi::class.java)
}

