package com.example.kotlinmessenger.Models

class User( val uid:String,val username:String, val profileImageUrl: String){
    constructor():this("","","")
}
