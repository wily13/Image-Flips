package com.wily.imageapp.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wily.imageapp.data.source.local.entity.ImageEntity

@Database(
    entities = [ImageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ImageDatabase: RoomDatabase() {
    abstract fun imagesDao(): ImageDao

    companion object {
        @Volatile
        private var INSTANCE: ImageDatabase? = null

        fun getInstance(context: Context): ImageDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ImageDatabase::class.java,
                    "Image_db"
                ).build()
            }
    }
}