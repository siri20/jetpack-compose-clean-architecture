package com.siri.core.domain.usecase.product.db

import com.siri.core.data.datasource.local.db.entity.ProductEntity
import com.siri.core.domain.repository.product.DbProductRepository
import com.siri.core.domain.usecase.BaseUseCaseSuspend
import javax.inject.Inject

/** Created by github.com/im-o on 5/2/2023. */

class InsertProductDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCaseSuspend<ProductEntity, Long>() {
    override suspend fun execute(params: ProductEntity): Long {
        return dbProductRepository.insertProductDb(params)
    }
}