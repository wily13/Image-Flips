package com.wily.imageapp.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.wily.imageapp.R
import com.wily.imageapp.databinding.ActivityDetailImageBinding
import com.wily.imageapp.ui.share.ShareActivity


class DetailImageActivity : AppCompatActivity() {
    companion object {
        const val TAG = "DetailImageActivity"
        const val EXTRA_IMAGES = "extra_images"
        const val EXTRA_URL = "extra_url"
        val REQUEST_CODE = 100
    }

    private lateinit var activityDetailImageBinding: ActivityDetailImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailImageBinding = ActivityDetailImageBinding.inflate(layoutInflater)
        setContentView(activityDetailImageBinding.root)

        supportActionBar?.title = getString(R.string.title_detail_images)

        val extras = intent.extras
        if (extras != null) {
            val imageId = extras.getString(EXTRA_IMAGES)
            val url = extras.getString(EXTRA_URL)

            activityDetailImageBinding.edtTitleImage.visibility = View.GONE

            activityDetailImageBinding.apply {
                Glide.with(this@DetailImageActivity)
                    .load(url)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgMeme)
            }

            activityDetailImageBinding.btnAddImage.setOnClickListener {
                openGalleryForImage()
                activityDetailImageBinding.llSaveShare.visibility = View.VISIBLE
            }

            activityDetailImageBinding.tvAdd.setOnClickListener {
                activityDetailImageBinding.edtTitleImage.visibility = View.VISIBLE
                activityDetailImageBinding.llSaveShare.visibility = View.VISIBLE
            }

            activityDetailImageBinding.btnSave.setOnClickListener {

            }

            activityDetailImageBinding.btnShare.setOnClickListener {
                val intent = Intent(this, ShareActivity::class.java)
                intent.putExtra(ShareActivity.EXTRA_URL, url)
                startActivity(intent)
            }
        }

    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            activityDetailImageBinding.imgMeme.setImageURI(data?.data)
        }
    }

}