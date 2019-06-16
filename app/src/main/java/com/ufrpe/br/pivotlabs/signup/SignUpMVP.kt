package com.ufrpe.br.pivotlabs.signup

interface SignUpMVP{

    interface SignUpActivityImpl{
        fun setUsername(username:String)
        fun setEmail(email:String)
        fun setPassword(email:String)
        fun usernameError():String
    }
}