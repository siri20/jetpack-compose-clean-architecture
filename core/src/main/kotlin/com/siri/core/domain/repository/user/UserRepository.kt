package com.siri.core.domain.repository.user

import com.siri.core.data.model.User
import com.siri.core.data.model.UsersResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsersApiCall(): Flow<UsersResponse> // this is sample not using `suspend`
    fun getUserByIdApiCall(id: Int): Flow<User>
}