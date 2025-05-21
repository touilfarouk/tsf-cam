package com.farouk.binance.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DelivererEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun delivererDao(): DelivererDao
}