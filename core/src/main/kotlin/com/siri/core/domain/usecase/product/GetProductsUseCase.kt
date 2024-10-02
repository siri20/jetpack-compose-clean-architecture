package com.siri.core.domain.usecase.product

import com.siri.core.data.model.ProductResponse
import com.siri.core.domain.repository.product.ProductRepository
import com.siri.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, Flow<ProductResponse>>() {
    override fun execute(params: Unit): Flow<ProductResponse> {
        return productRepository.getProductsApiCall()
    }
}