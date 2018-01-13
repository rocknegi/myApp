package com.example.rohit.loginactivity

import okhttp3.MediaType



val scheme = "http"
//Use ngrok to tunnel the registration api server and update this string
val baseUrl = "ddcd2754.ngrok.io"



val loginClientUrl = "api/client/login"
val registerClientUrl = "api/client"
val adminIdUrl = "api/admin/id"
val linkAdminUrl = "api/client/idcode"


val JSON = MediaType.parse("application/json; charset=utf-8")

val clientPreferenceFileKey = "1234asd"


val googleUrl = "http://www.google.com"

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

