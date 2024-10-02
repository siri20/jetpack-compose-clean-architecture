package com.siri.core.data.repository.product

import com.siri.core.data.datasource.remote.ApiService
import com.siri.core.data.model.Product
import com.siri.core.data.model.ProductResponse
import com.siri.core.domain.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {

    override fun getProductsApiCall(): Flow<ProductResponse> { // this is sample not using `suspend`
        return flow {
            emit(apiService.getProducts())
        }
    }

    override fun getProductByIdApiCall(id: Int): Flow<Product> {
        return flow {
            emit(apiService.getProductById(id))
        }
    }

    override suspend fun searchProductApiCall(query: String): Flow<ProductResponse> {
        return flowOf(apiService.searchProduct(query))
    }
}