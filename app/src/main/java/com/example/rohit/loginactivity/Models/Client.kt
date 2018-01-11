package com.example.rohit.loginactivity.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by sourabh on 1/11/2018.
 */

@Entity(tableName = "client")
class Client {
    @PrimaryKey
    public var id: Int = 0

    public lateinit var  name: String
    public lateinit var username: String
    public var linked: Boolean = false


}