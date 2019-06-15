package com.ufrpe.br.pivotlabs.login

interface LoginMVP {

    interface ModelImpl {
        fun login(email: String, password: String)
        fun logout()
        fun getEmail(): String
    }

    interface PresenterImpl {
        fun login(email: String, password: String)
        fun logout()
        fun getEmail(): String
        fun setView(view: ViewImpl)
        fun showProgressBar(status: Boolean)
    }

    interface ViewImpl {
        fun showProgressBar(visible: Int)
        fun emailError()
        fun passwordError()
    }

}