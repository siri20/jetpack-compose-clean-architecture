package com.siri.core.domain.repository.product

import com.siri.core.data.datasource.local.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

/** 12/27/2022. */

interface DbProductRepository {
    fun getProductsDb(): Flow<MutableList<ProductEntity>>
    fun getProductByIdDb(id: Long): Flow<ProductEntity>
    suspend fun insertProductDb(product: ProductEntity): Long
    suspend fun deleteProductDb(product: ProductEntity): Int
}