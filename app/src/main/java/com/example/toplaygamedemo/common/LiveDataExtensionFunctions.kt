package com.example.toplaygamedemo.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @author : Mingaleev D
 * @data : 12/01/2023
 */

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>
                                ){
   observe(lifecycleOwner, object :Observer<T>{
      override fun onChanged(t: T?) {
         removeObserver(this)
         observer.onChanged(t)
      }
   })
}