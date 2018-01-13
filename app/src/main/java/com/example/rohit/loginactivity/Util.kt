package com.example.rohit.loginactivity

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.SocketTimeoutException

/**
 * Created by sourabh on 1/13/2018.
 */

fun getString(context: Context, key: String,defValue : String = ""): String =
    context.getSharedPreferences(clientPreferenceFileKey, Context.MODE_PRIVATE).getString(key,defValue)

fun getBoolean(context: Context, key: String,defValue : Boolean = false): Boolean =
        context.getSharedPreferences(clientPreferenceFileKey, Context.MODE_PRIVATE).getBoolean(key,defValue)


fun isNetworkAvailable(activity: Activity): Boolean {
    var connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var activeNetworkInfo = connectivityManager.activeNetworkInfo
    val connected = activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected
    if (connected) {
        var client = OkHttpClient()
        var request = Request.Builder().url(googleUrl).build()
        try{
            client.newCall(request).execute()
        }catch (exc: SocketTimeoutException){
            Log.i("error", exc.toString())
            return false
        }catch(exc: Exception){
            Log.i("error", exc.toString())
            exc.printStackTrace()
            return false
        }
        return true
    }
    return false
}
