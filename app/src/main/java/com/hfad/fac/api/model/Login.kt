package com.hfad.fac.api.model

class Login(user:String, password:String) {
    private var user:String = ""
    private var password:String = ""
     init {
         this.user = user
         this.password = password
     }
}