package com.siri.sample.ui.landing

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.siri.core.data.UserUiState
import com.siri.core.data.model.UsersResponse
import com.siri.core.domain.usecase.user.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: UserUseCase
) : ViewModel() {

    private val _uiStateUser: MutableStateFlow<UserUiState<UsersResponse>> = MutableStateFlow(
        UserUiState.Loading)
    val uiStateUser: StateFlow<UserUiState<UsersResponse>> = _uiStateUser

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getProductsApiCall() { // this is sample not using `suspend`
        getUserUseCase.execute(Unit).onEach { user ->
            _uiStateUser.value = UserUiState.Success(user)
        }.catch { e ->
            _uiStateUser.value = UserUiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }
}