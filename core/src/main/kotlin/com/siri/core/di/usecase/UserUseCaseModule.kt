package com.siri.core.di.usecase

import com.siri.core.domain.repository.user.UserRepository
import com.siri.core.domain.usecase.user.GetUserByIdUseCase
import com.siri.core.domain.usecase.user.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UserUseCaseModule {
    @Provides
    fun provideUserUseCase(userRepository: UserRepository): UserUseCase {
        return UserUseCase(userRepository)
    }

    @Provides
    fun provideGetUserByIdUseCase(userRepository: UserRepository): GetUserByIdUseCase {
        return GetUserByIdUseCase(userRepository)
    }
}