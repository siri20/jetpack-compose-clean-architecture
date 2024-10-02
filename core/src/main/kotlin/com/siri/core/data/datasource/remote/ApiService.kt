package com.siri.core.data.datasource.remote

import com.siri.core.data.model.Product
import com.siri.core.data.model.ProductResponse
import retrofit2.http.*

/** Created by github.com/im-o on 10/1/2022. */

interface ApiService {

    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") query: String
    ): ProductResponse
}