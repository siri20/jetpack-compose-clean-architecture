package com.siri.core.data.datasource.remote

import com.siri.core.data.model.Product
import com.siri.core.data.model.ProductResponse
import com.siri.core.data.model.User
import com.siri.core.data.model.UsersResponse
import retrofit2.http.*


interface ApiService {

    @GET("users")
    suspend fun getUsers(): UsersResponse

    @GET("users/{id}")
    suspend fun getUserDetails(
        @Path("id") id: Int
    ): User

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