package com.siri.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.siri.core.data.datasource.local.db.AppDatabase
import com.siri.core.data.datasource.remote.ApiService
import com.siri.core.data.repository.product.DbProductRepositoryImpl
import com.siri.core.data.repository.product.UserRepositoryImpl
import com.siri.core.data.repository.product.ProductRepositoryImpl
import com.siri.core.domain.repository.user.UserRepository
import com.siri.core.domain.repository.product.DbProductRepository
import com.siri.core.domain.repository.product.ProductRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePracticeRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDbProductRepository(db: AppDatabase): DbProductRepository {
        return DbProductRepositoryImpl(db)
    }
}