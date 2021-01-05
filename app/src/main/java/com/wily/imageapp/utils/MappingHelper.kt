package com.wily.imageapp.utils

import com.wily.imageapp.data.source.local.entity.ImageEntity
import com.wily.imageapp.data.source.remote.response.MemesItem

object MappingHelper {

    fun mapFromEntityImage(entity: MemesItem): ImageEntity {
        return ImageEntity(
            imageId = entity.id,
            name = entity.name,
            url = entity.url,
            width = entity.width,
            height = entity.height
        )
    }

    fun imagesMapFromEntityList(entities: ArrayList<MemesItem>): ArrayList<ImageEntity> {
        return entities.map { mapFromEntityImage(it) } as ArrayList<ImageEntity>
    }
}