package com.farouk.binance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.farouk.binance.domain.model.Deliverer


@Dao
interface DelivererDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeliverer(deliverer: DelivererEntity)
}