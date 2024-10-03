package com.siri.core.domain.usecase.user

import com.siri.core.data.model.User
import com.siri.core.domain.repository.user.UserRepository
import com.siri.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Int, Flow<User>>() {
    override fun execute(params: Int): Flow<User> {
        return userRepository.getUserByIdApiCall(params)
    }
}