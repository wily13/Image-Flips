package com.wily.imageapp.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wily.imageapp.api.ApiConfig
import com.wily.imageapp.data.source.local.entity.ImageEntity
import com.wily.imageapp.data.source.remote.LocalDataSource
import com.wily.imageapp.data.source.remote.response.ImageResponse
import com.wily.imageapp.data.source.remote.response.MemesItem
import com.wily.imageapp.utils.MappingHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRepository: ImageDataSource {

    private val listImage = MutableLiveData<List<ImageEntity>>()

    companion object{
        const val TAG = "ImageRepository"
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: ImageRepository? = null

    }

    fun getInstance(): ImageRepository =
        instance
            ?: synchronized(this) {
                instance
                    ?: ImageRepository()
            }

    override fun getAllImage(): LiveData<List<ImageEntity>> {
        CoroutineScope(Dispatchers.IO).launch {
            delay(SERVICE_LATENCY_IN_MILLIS)

            val client = ApiConfig.getApiService().getAllImage()
            client.enqueue(object : Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    if (response.isSuccessful) {
                        val listMapMovies =
                            response.body()?.data?.memes?.let { MappingHelper.imagesMapFromEntityList(
                                it as ArrayList<MemesItem>
                            ) }
                        if (listMapMovies != null) {
                            listImage.postValue(listMapMovies)
                        }

                    } else {
                        Log.e(TAG, "notSuccessfull Response: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
        return listImage
    }

//    override fun setImage(moviesId: Int): LiveData<ImageEntity> {
//        localDataSource.insertMovie(moviesList)
//    }


}