package com.siri.core.domain.usecase.product

import com.siri.core.domain.usecase.product.GetProductsUseCase
import com.siri.core.data.model.Product
import com.siri.core.data.model.ProductResponse
import com.siri.core.domain.repository.product.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.whenever

/** Created by github.com/im-o on 5/2/2023.  */

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetProductsUseCaseTest {

    @Mock
    private lateinit var productRepository: ProductRepository
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setUp() {
        getProductsUseCase = GetProductsUseCase(productRepository)
    }

    @Test
    fun `execute should return flow of product response`() = runTest {
        // given
        val productResponse = ProductResponse(0, mutableListOf(Product("Product", "Product 1")))
        whenever(productRepository.getProductsApiCall()).thenReturn(flowOf(productResponse))

        // when
        val result = getProductsUseCase.execute(Unit).first()

        // then
        assertEquals(productResponse, result)
    }

    @Test
    fun `execute should return error flow when an exception occurs`() = runTest {
        // Given
        val exception = Exception("An error occurred")
        val flow: Flow<ProductResponse> = flow { throw exception }
        given(productRepository.getProductsApiCall()).willReturn(flow)

        // When
        val result = runCatching { getProductsUseCase.execute(Unit).first() }

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}