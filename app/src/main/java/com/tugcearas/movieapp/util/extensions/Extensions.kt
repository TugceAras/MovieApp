package com.tugcearas.movieapp.util.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tugcearas.movieapp.util.constants.Constants

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun Context.toastMessage(message:String) = Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

fun View.click(func:()-> Unit){
    this.setOnClickListener {
        func()
    }
}

fun ImageView.downloadImage(imageUrl:String?){
    imageUrl?.let {
        Glide.with(context).load(Constants.IMAGE_URL + it).into(this)
    }
}

@BindingAdapter("android:downloadImage")
fun bindImage(imageView:ImageView, imageUrl:String?){
    imageView.downloadImage(imageUrl)
}
