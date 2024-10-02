package com.siri.core.domain.usecase.user

import com.siri.core.data.model.UsersResponse
import com.siri.core.domain.repository.practice.PracticeRepository
import com.siri.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val practiceRepository: PracticeRepository
) : BaseUseCase<Unit, Flow<UsersResponse>>() {
    override fun execute(params: Unit): Flow<UsersResponse> {
        return practiceRepository.getUsersApiCall()
    }
}