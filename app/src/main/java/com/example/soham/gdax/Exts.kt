package com.example.soham.gdax

import android.util.Log

/**
 * Created by soham on 8/12/17.
 */
fun Any.e(any: Any? = "no message provided"){
    Log.e(this.javaClass.simpleName + "`~", any.toString())
}