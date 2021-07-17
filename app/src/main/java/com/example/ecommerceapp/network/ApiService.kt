package com.example.ecommerceapp.network

import com.example.ecommerceapp.model.ProductListModel
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProductList(): List<ProductListModel>
}