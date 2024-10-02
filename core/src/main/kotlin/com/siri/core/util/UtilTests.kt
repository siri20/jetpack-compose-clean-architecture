package com.siri.core.util

import com.siri.core.data.model.Product
import com.siri.core.data.model.ProductResponse

/** Created by github.com/im-o on 5/3/2023. */

object UtilTests {
    val dummyProduct = Product("Product", "Product 1")
    val dummyProductResponse = ProductResponse(0, mutableListOf(dummyProduct))
}