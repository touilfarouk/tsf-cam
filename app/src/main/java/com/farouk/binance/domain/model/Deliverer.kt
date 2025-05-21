package com.farouk.binance.domain.model

data class Deliverer(
    val delivererId: Int=0, // Nullable for new entries
    val name:String,
)
