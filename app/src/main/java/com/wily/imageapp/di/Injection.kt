package com.wily.imageapp.di

import com.wily.imageapp.data.source.ImageRepository

object Injection {
    fun provideRepository(): ImageRepository {
        return ImageRepository().getInstance()
    }
}