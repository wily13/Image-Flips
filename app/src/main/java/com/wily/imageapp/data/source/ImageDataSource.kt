package com.wily.imageapp.data.source

import androidx.lifecycle.LiveData
import com.wily.imageapp.data.source.local.entity.ImageEntity

interface ImageDataSource {

    fun getAllImage(): LiveData<List<ImageEntity>>

//    fun setImage(moviesId: Int): LiveData<ImageEntity>
}