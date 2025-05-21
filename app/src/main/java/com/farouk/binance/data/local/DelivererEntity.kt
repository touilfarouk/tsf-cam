package com.farouk.binance.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "deliverer")
data class DelivererEntity (
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val name:String,
    val query:String,
)

