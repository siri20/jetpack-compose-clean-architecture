package com.siri.core.data.repository.product

import com.siri.core.data.datasource.remote.ApiService
import com.siri.core.data.model.User
import com.siri.core.data.model.UsersResponse
import com.siri.core.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {

    override fun getUsersApiCall(): Flow<UsersResponse> { // this is sample not using `suspend`
        return flow {
            emit(apiService.getUsers())
        }
    }

    override fun getUserByIdApiCall(id: Int): Flow<User> {
        return flow {
            emit(apiService.getUserDetails(id))
        }
    }
}