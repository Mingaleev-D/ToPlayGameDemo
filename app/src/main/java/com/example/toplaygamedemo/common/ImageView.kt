package com.example.toplaygamedemo.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.toplaygamedemo.R

/**
 * @author : Mingaleev D
 * @data : 8/01/2023
 */

fun ImageView.load(url: String){
   Glide.with(context)
      .load(url)
      .centerCrop()
      .placeholder(R.drawable.ic_error)
      .into(this)
}