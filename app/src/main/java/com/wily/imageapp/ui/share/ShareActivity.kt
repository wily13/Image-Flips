package com.wily.imageapp.ui.share

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.wily.imageapp.R
import com.wily.imageapp.databinding.ActivityShareBinding
import com.wily.imageapp.ui.detail.DetailImageActivity

class ShareActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_URL = "extra_url"
    }

    private lateinit var activityShareBinding: ActivityShareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShareBinding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(activityShareBinding.root)

        supportActionBar?.title = "Detail Images"

        val extras = intent.extras
        if (extras != null) {
            val url = extras.getString(DetailImageActivity.EXTRA_URL)

            activityShareBinding.apply {
                Glide.with(this@ShareActivity)
                    .load(url)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgMeme)
            }

        }

        activityShareBinding.btnFacebook.setOnClickListener {

        }

        activityShareBinding.btnTwitter.setOnClickListener {

        }

    }
}