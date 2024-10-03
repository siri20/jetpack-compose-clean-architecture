package com.siri.user.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.siri.core.data.ProductUiState
import com.siri.core.data.datasource.local.db.entity.ProductEntity
import com.siri.core.data.model.Product
import com.siri.core.data.model.mapper.ProductMapper.mapFromProductToEntity
import com.siri.core.domain.usecase.product.GetProductByIdUseCase
import com.siri.core.domain.usecase.product.db.GetProductByIdDbUseCase
import com.siri.core.domain.usecase.product.db.InsertProductDbUseCase
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
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val getProductByIdDbUseCase: GetProductByIdDbUseCase,
    private val insertProductDbUseCase: InsertProductDbUseCase,
) : ViewModel() {

    private val _Product_uiStateProduct: MutableStateFlow<ProductUiState<Product>> = MutableStateFlow(ProductUiState.Loading)
    val productUiStateProduct: StateFlow<ProductUiState<Product>> = _Product_uiStateProduct

    private val _Product_uiStateDbProduct: MutableStateFlow<ProductUiState<ProductEntity>> = MutableStateFlow(
        ProductUiState.Loading)
    val productUiStateDbProduct: StateFlow<ProductUiState<ProductEntity>> = _Product_uiStateDbProduct

    fun getProductByIdApiCall(id: Int) {
        getProductByIdUseCase.execute(id).onEach {
            _Product_uiStateProduct.value = ProductUiState.Success(it)
        }.catch { e ->
            _Product_uiStateProduct.value = ProductUiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun getProductByIdDb(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                getProductByIdDbUseCase.execute(id).catch {
                    _Product_uiStateDbProduct.value = ProductUiState.Error(it.message.toString())
                }.collect { product ->
                    _Product_uiStateDbProduct.value = ProductUiState.Success(product)
                }
            } catch (e: Exception) {
                _Product_uiStateDbProduct.value = ProductUiState.Error(e.message.toString())
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