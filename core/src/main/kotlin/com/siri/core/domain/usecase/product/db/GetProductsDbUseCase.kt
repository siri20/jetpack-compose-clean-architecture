package com.siri.core.domain.usecase.product.db

import com.siri.core.data.datasource.local.db.entity.ProductEntity
import com.siri.core.domain.repository.product.DbProductRepository
import com.siri.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Created by github.com/im-o on 5/2/2023. */

class GetProductsDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCase<Unit, Flow<MutableList<ProductEntity>>>() {
    override fun execute(params: Unit): Flow<MutableList<ProductEntity>> {
        return dbProductRepository.getProductsDb()
    }
}