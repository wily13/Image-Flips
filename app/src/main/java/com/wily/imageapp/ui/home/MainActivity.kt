package com.wily.imageapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wily.imageapp.R
import com.wily.imageapp.databinding.ActivityMainBinding
import com.wily.imageapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        supportActionBar?.title = "Image Flips"

        populateImage()

        activityMainBinding.swiperefresh.setOnRefreshListener {
            populateImage()
            activityMainBinding.swiperefresh.isRefreshing = false
        }

    }

    private fun populateImage(){
        val repository = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, repository).get(MainViewModel::class.java)
        imageAdapter = ImageAdapter()

        activityMainBinding.progressBar.visibility = View.VISIBLE
        viewModel.getAllImages().observe(this, Observer { images ->
            activityMainBinding.progressBar.visibility = View.GONE
            if (images != null) {
                imageAdapter.setMovies(images)
                imageAdapter.notifyDataSetChanged()
            }

        })

        with(activityMainBinding.rvImages) {
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            adapter = imageAdapter
        }
    }
}