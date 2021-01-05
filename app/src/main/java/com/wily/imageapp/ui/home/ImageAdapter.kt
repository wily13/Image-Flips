package com.wily.imageapp.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wily.imageapp.R
import com.wily.imageapp.data.source.local.entity.ImageEntity
import com.wily.imageapp.data.source.remote.response.MemesItem
import com.wily.imageapp.databinding.ItemsImagesBinding
import com.wily.imageapp.ui.detail.DetailImageActivity

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.MoviesViewHolder>() {

    private var listMovies = ArrayList<ImageEntity>()

    fun setMovies(movies: List<ImageEntity>?) {
        if (movies.isNullOrEmpty()) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemsImagesBinding =
            ItemsImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemsImagesBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MoviesViewHolder(private val binding: ItemsImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageEntity: ImageEntity) {
            with(binding) {
                imageEntity.apply {

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailImageActivity::class.java)
                        intent.putExtra(DetailImageActivity.EXTRA_IMAGES, imageId)
                        intent.putExtra(DetailImageActivity.EXTRA_URL, url)
                        itemView.context.startActivity(intent)
                    }

                    Glide.with(itemView.context)
                        .load(url)
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                        )
                        .into(imgPoster)
                }
            }
        }
    }


}