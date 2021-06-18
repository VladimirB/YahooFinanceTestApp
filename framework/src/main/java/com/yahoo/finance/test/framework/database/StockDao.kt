package com.yahoo.finance.test.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface StockDao {

    @Query("SELECT * FROM stocks")
    fun getAll(): Single<List<StockLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stockLocal: StockLocal): Single<Long>
}