package com.wily.imageapp.data.source.remote

import com.wily.imageapp.data.source.local.entity.ImageEntity
import com.wily.imageapp.data.source.local.room.ImageDao

class LocalDataSource private constructor(private val mImageDao: ImageDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mImageDao: ImageDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(mImageDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun insertMovie(images: List<ImageEntity>) {
        mImageDao.insertImage(images)
    }
}