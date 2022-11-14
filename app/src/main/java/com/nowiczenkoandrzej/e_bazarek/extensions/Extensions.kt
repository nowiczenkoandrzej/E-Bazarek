package com.nowiczenkoandrzej.e_bazarek.extensions

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.nowiczenkoandrzej.e_bazarek.presentation.activities.ContentActivity

fun Fragment.startApp(context: Context){
    Intent(context, ContentActivity::class.java).apply {
        flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }.also {
        startActivity(it)
    }

}


fun AppCompatActivity.startApp(context: Context){
    Intent(context, ContentActivity::class.java).apply {
        flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }.also {
        startActivity(it)
    }

}

fun Fragment.quickToast(message: String){
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun View.hide(){
    this.isVisible = false
}

fun View.show(){
    this.isVisible = true
}

fun View.makeMoreTransparent(){
    AlphaAnimation(1.0F, 0.2F).apply {
        duration = 250
        fillAfter = true
    }.also {
        startAnimation(it)
    }
}

fun View.makeLessTransparent(){
    AlphaAnimation(0.2F, 1.0F).apply {
        duration = 250
        fillAfter = true
    }.also {
        startAnimation(it)
    }
}