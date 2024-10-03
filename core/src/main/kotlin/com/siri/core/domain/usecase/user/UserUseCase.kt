package com.siri.core.domain.usecase.user

import com.siri.core.data.model.UsersResponse
import com.siri.core.domain.repository.user.UserRepository
import com.siri.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Unit, Flow<UsersResponse>>() {
    override fun execute(params: Unit): Flow<UsersResponse> {
        return userRepository.getUsersApiCall()
    }
}