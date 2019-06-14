package com.ufrpe.br.pivotlabs.login.data

interface LoginServiceApi {

    fun login(email: String, password: String)
    fun loginFacebook()

    fun logout()

}