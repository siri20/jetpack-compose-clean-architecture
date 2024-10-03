package com.siri.user.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.siri.core.data.ProductUiState
import com.siri.core.data.datasource.local.db.entity.ProductEntity
import com.siri.core.domain.usecase.product.db.DeleteProductDbUseCase
import com.siri.core.domain.usecase.product.db.GetProductsDbUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CartViewModel @Inject constructor(
    private val getProductsDbUseCase: GetProductsDbUseCase,
    private val deleteProductDbUseCase: DeleteProductDbUseCase,
) : ViewModel() {

    private val _Product_uiStateDbProducts: MutableStateFlow<ProductUiState<MutableList<ProductEntity>>> = MutableStateFlow(
        ProductUiState.Loading)
    val productUiStateDbProducts: StateFlow<ProductUiState<MutableList<ProductEntity>>> = _Product_uiStateDbProducts

    fun getProductsDb(dispatcher: CoroutineDispatcher = Dispatchers.Default) {
        viewModelScope.launch(dispatcher) {
            try {
                getProductsDbUseCase.execute(Unit).catch {
                    _Product_uiStateDbProducts.value = ProductUiState.Error(it.message.toString())
                }.collect { product ->
                    _Product_uiStateDbProducts.value = ProductUiState.Success(product)
                }
            } catch (e: Exception) {
                _Product_uiStateDbProducts.value = ProductUiState.Error(e.message.toString())
            }
        }
    }

    fun deleteProductDb(product: ProductEntity) {
        viewModelScope.launch {
            val intDelete = deleteProductDbUseCase.execute(product)
            if (intDelete == 1) getProductsDb()
        }
    }
}