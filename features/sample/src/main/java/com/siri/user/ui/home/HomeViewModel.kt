package com.siri.user.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.siri.core.data.ProductUiState
import com.siri.core.data.model.ProductResponse
import com.siri.core.domain.usecase.product.GetProductsUseCase
import com.siri.core.domain.usecase.product.SearchProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductUseCase: SearchProductUseCase
) : ViewModel() {

    private val _Product_uiStateProduct: MutableStateFlow<ProductUiState<ProductResponse>> = MutableStateFlow(
        ProductUiState.Loading)
    val productUiStateProduct: StateFlow<ProductUiState<ProductResponse>> = _Product_uiStateProduct

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun getProductsApiCall() { // this is sample not using `suspend`
        getProductsUseCase.execute(Unit).onEach { product ->
            _Product_uiStateProduct.value = ProductUiState.Success(product)
        }.catch { e ->
            _Product_uiStateProduct.value = ProductUiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun searchProductApiCall(query: String) {
        _query.value = query
        viewModelScope.launch {
            try {
                searchProductUseCase.execute(_query.value)
                    .catch {
                        _Product_uiStateProduct.value = ProductUiState.Error(it.message.toString())
                    }
                    .collect { product ->
                        _Product_uiStateProduct.value = ProductUiState.Success(product)
                    }
            } catch (e: Exception) {
                _Product_uiStateProduct.value = ProductUiState.Error(e.message.toString())
            }
        }
    }
}