package com.techlads.myapplication.utils

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.techlads.myapplication.R


/**
 * Created by Yasir on 11/3/2020.
 */

fun ImageView.loadUrl(url: String) {

    Glide.with(context).load(url).centerCrop().placeholder(R.drawable.defaultimg)
        .error(R.drawable.defaultimg).into(this)
}



fun View.addOverlayedBackground(url : String) {


    Glide.with(context)
        .load(url)
        .skipMemoryCache(true) //Will keep bitmaps in control
        .centerCrop()
        .error(R.drawable.defaultimg)
        .into(object : CustomTarget<Drawable>(measuredWidth, measuredHeight){

            override fun onLoadCleared(placeholder: Drawable?) {

            }

            override fun onResourceReady(
                resource: Drawable,
                transition: com.bumptech.glide.request.transition.Transition<in Drawable>?) {

                background = LayerDrawable(
                    arrayOf(
                        resource,
                        ResourcesCompat.getDrawable(context.resources, R.drawable.default_overlay, null)
                    )
                )
            }
        })
}


fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.inflate(@LayoutRes layout: Int , inMe: Boolean = false) : View{

    return LayoutInflater.from(context).inflate(layout , if (inMe) this else null , inMe)
}

fun RecyclerView.setLayoutManager(){
    layoutManager = LinearLayoutManager(context , RecyclerView.HORIZONTAL , false)
}