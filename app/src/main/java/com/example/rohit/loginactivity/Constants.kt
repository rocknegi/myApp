package com.example.rohit.loginactivity

import okhttp3.MediaType


//Use ngrok to tunnel the registration api server and update this string
val baseUrl = "http://774659f1.ngrok.io"



val loginClientUrl = "/api/client/login"
val registerClientUrl = "/api/client"


val JSON = MediaType.parse("application/json; charset=utf-8")

val clientPreferenceFileKey = "1234asd"

//fun isNetworkAvailable(): Boolean {
//    var connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    var activeNetworkInfo = connectivityManager.activeNetworkInfo
//    val connected = activeNetworkInfo != null && activeNetworkInfo.isAvailable && activeNetworkInfo.isConnected
//    if (connected) {
//        var client = OkHttpClient()
//        var request = Request.Builder().url(googleUrl).build()
//        try{
//            client.newCall(request).execute()
//        }catch (exc: SocketTimeoutException){
//            Log.i("error", exc.message)
//            return false
//        }catch(exc: Exception){
//            Log.i("error", exc.message)
//            return false
//        }
//        return true
//    }
//    return false
//}