package com.farouk.binance.data.remote

import com.farouk.binance.domain.model.Deliverer
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("deliverers/reg1/New%20Supplier")
    suspend fun getDeliverers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<Deliverer>

}