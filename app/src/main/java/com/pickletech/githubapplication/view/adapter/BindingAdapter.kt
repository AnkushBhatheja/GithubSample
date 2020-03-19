package com.pickletech.githubapplication.view.adapter

import android.text.TextUtils
import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pickletech.githubapplication.R

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(view: ImageView, url: String?) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.context).load(url).error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher).into(view)
        } else {
            view.setImageDrawable(null)
        }
    }

    @JvmStatic
    @BindingAdapter("webviewUrl")
    fun webviewUrl(webView: WebView, url: String?) {
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url)
        }
    }
}