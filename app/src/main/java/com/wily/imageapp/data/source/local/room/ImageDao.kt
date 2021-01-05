package com.wily.imageapp.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.wily.imageapp.data.source.local.entity.ImageEntity

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(movies: List<ImageEntity>): LongArray
}