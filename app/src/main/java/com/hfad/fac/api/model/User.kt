package com.hfad.fac.api.model

public class User {
    private var id:Int = 0
    private var email:String = ""
    private var token:String = ""

    fun getId():Int {
        return id
    }

    fun setId(id:Int) {
        this.id = id
    }

    fun getEmail():String {
        return email
    }

    fun setEmail(email:String) {
        this.email = email
    }

    fun getToken():String {
        return token
    }

    fun setToken(token:String) {
        this.token = token
    }

}