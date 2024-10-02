package com.siri.sample.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.siri.core.data.UiState
import com.siri.core.data.UserUiState
import com.siri.core.data.datasource.local.db.entity.ProductEntity
import com.siri.core.data.model.Product
import com.siri.core.data.model.User
import com.siri.core.data.model.mapper.ProductMapper.mapFromProductToEntity
import com.siri.core.domain.usecase.product.db.GetProductByIdDbUseCase
import com.siri.core.domain.usecase.product.db.InsertProductDbUseCase
import com.siri.core.domain.usecase.user.GetUserByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val geetUserByIdUseCase: GetUserByIdUseCase,
    private val getProductByIdDbUseCase: GetProductByIdDbUseCase,
    private val insertProductDbUseCase: InsertProductDbUseCase,
) : ViewModel() {

    private val _uiStateUser: MutableStateFlow<UserUiState<User>> = MutableStateFlow(UserUiState.Loading)
    val uiStateUser: StateFlow<UserUiState<User>> = _uiStateUser

    private val _uiStateDbProduct: MutableStateFlow<UiState<ProductEntity>> = MutableStateFlow(
        UiState.Loading)
    val uiStateDbProduct: StateFlow<UiState<ProductEntity>> = _uiStateDbProduct

    fun getUserByIdApiCall(id: Int) {
        geetUserByIdUseCase.execute(id).onEach {
            _uiStateUser.value = UserUiState.Success(it)
        }.catch { e ->
            _uiStateUser.value = UserUiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun getProductByIdDb(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                getProductByIdDbUseCase.execute(id).catch {
                    _uiStateDbProduct.value = UiState.Error(it.message.toString())
                }.collect { product ->
                    _uiStateDbProduct.value = UiState.Success(product)
                }
            } catch (e: Exception) {
                _uiStateDbProduct.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun insertProductDb(product: Product) {
        viewModelScope.launch {
            val longInsertStatus = insertProductDbUseCase.execute(mapFromProductToEntity(product))
            if (longInsertStatus > 0) getProductByIdDb((product.id ?: -1).toLong())
        }
    }
}