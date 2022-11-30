package com.kerencev.companies.presentation.base

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kerencev.companies.BuildConfig
import com.kerencev.companies.R

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun ImageView.loadCircle(url: String) {
    val result = "${BuildConfig.BASE_URL}$url"
    Glide.with(this.context)
        .load(result)
        .circleCrop()
        .transition(DrawableTransitionOptions.withCrossFade(300))
        .placeholder(R.drawable.place_holder)
        .error(R.drawable.error_loading)
        .into(this)
}