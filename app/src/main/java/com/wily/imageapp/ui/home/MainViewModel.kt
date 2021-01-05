package com.wily.imageapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wily.imageapp.data.source.ImageRepository
import com.wily.imageapp.data.source.local.entity.ImageEntity

class MainViewModel(private val mImageRepository: ImageRepository): ViewModel() {

    fun getAllImages(): LiveData<List<ImageEntity>> = mImageRepository.getAllImage()
}