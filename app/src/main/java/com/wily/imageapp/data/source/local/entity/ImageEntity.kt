package com.wily.imageapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imagesentities")
class ImageEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "imageId")
    var imageId: String,

    @NonNull
    @ColumnInfo(name = "name")
    var name: String,

    @NonNull
    @ColumnInfo(name = "url")
    val url: String,

    @NonNull
    @ColumnInfo(name = "width")
    val width: Int,

    @NonNull
    @ColumnInfo(name = "height")
    val height: Int
)