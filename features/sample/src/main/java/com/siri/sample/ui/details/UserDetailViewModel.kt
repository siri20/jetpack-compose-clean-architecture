package com.siri.sample.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siri.core.data.UserUiState
import com.siri.core.data.model.User
import com.siri.core.domain.usecase.user.GetUserByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _uiStateUser: MutableStateFlow<UserUiState<User>> = MutableStateFlow(UserUiState.Loading)
    val uiStateUser: StateFlow<UserUiState<User>> = _uiStateUser

    fun getUserByIdApiCall(id: Int) {
        getUserByIdUseCase.execute(id).onEach {
            _uiStateUser.value = UserUiState.Success(it)
        }.catch { e ->
            _uiStateUser.value = UserUiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }
}