package com.closerbuy.vendor.presentation.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Vijay on 4/4/20.
 */

class GlideUtils {

    companion object {

        fun setImageToImageView(context: Context, imagePath: String?, imageViewId: ImageView?) {
            Glide.with(context).load(imagePath)
                .into(imageViewId)
        }

    }

}