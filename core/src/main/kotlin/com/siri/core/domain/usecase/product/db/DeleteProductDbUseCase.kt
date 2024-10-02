package com.siri.core.domain.usecase.product.db

import com.siri.core.data.datasource.local.db.entity.ProductEntity
import com.siri.core.domain.repository.product.DbProductRepository
import com.siri.core.domain.usecase.BaseUseCaseSuspend
import javax.inject.Inject

/** 5/2/2023. */

class DeleteProductDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCaseSuspend<ProductEntity, Int>() {
    override suspend fun execute(params: ProductEntity): Int {
        return dbProductRepository.deleteProductDb(params)
    }
}