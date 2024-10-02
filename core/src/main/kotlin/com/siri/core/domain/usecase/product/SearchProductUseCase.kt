package com.siri.core.domain.usecase.product

import com.siri.core.data.model.ProductResponse
import com.siri.core.domain.repository.product.ProductRepository
import com.siri.core.domain.usecase.BaseUseCaseSuspend
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCaseSuspend<String, Flow<ProductResponse>>() {
    override suspend fun execute(params: String): Flow<ProductResponse> {
        return productRepository.searchProductApiCall(params)
    }
}