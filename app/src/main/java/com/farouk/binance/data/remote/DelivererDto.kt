package com.farouk.binance.data.remote


data class DelivererDto(
    val delivererId: Int=0, // Nullable for new entries
     val name:String,
)