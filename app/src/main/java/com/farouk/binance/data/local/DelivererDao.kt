package com.farouk.binance.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farouk.binance.domain.model.Deliverer

@Dao
interface DelivererDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeliverer(list: List<DelivererEntity>)

    @Query("SELECT * FROM DelivererEntity WHERE `query` = :q")
    fun getDeliverersByQuery(q: String): PagingSource<Int, DelivererEntity>

    @Query("DELETE FROM DelivererEntity WHERE `query` = :q")
    suspend fun nukeTable(q: String)

    @Query("SELECT COUNT(*) FROM DelivererEntity WHERE `query` = :q")
    suspend fun getCountCorrespondingToQuery(q: String): Int
}