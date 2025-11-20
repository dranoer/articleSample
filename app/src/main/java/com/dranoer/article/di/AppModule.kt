package com.dranoer.article.di

import com.dranoer.article.data.remote.ApiService
import com.dranoer.article.data.remote.MockInterceptor
import com.dranoer.article.data.repository.ArticleRepositoryImpl
import com.dranoer.article.domain.repository.ArticleRepository
import com.dranoer.shared.cache.InMemoryKeyValueCache
import com.dranoer.shared.cache.KeyValueCache
import dagger.Binds
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
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindArticleRepository(impl: ArticleRepositoryImpl): ArticleRepository

    companion object {
        @Provides
        fun provideOkHttpClient() = OkHttpClient.Builder()
            .addInterceptor(MockInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @Provides
        fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

        @Provides
        @Singleton
        fun provideKeyValueCache(): KeyValueCache = InMemoryKeyValueCache()
    }
}