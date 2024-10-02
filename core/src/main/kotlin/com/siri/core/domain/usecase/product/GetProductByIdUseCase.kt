package com.siri.core.domain.usecase.product

import com.siri.core.data.model.Product
import com.siri.core.domain.repository.product.ProductRepository
import com.siri.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<Int, Flow<Product>>() {
    override fun execute(params: Int): Flow<Product> {
        return productRepository.getProductByIdApiCall(params)
    }
}