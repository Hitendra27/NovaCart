package com.example.novacart.di

import com.example.novacart.data.remote.api.ProductApi
import com.example.novacart.domain.repository.ProductRepository
import com.example.novacart.domain.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApi(
        retrofit: Retrofit
    ): ProductApi {

        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        api: ProductApi
    ) : ProductRepository {

        return ProductRepositoryImpl(api)
    }
}