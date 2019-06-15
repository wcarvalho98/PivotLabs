package com.ufrpe.br.pivotlabs.login

import com.google.firebase.auth.FirebaseAuth

interface LoginMVP {

    interface ModelImpl {
        fun login(email: String, password: String)
        fun logout()
        fun getUser(): FirebaseAuth
        fun setUserName(name: String)
    }

    interface PresenterImpl {
        fun login(email: String, password: String)
        fun logout()
        fun getUser(): FirebaseAuth
        fun setUserName(name: String)
        fun setView(view: ViewImpl)
        fun showUI(status: Boolean)
        fun mainScreen()
        fun makeSnackbar(text: String)
    }

    interface ViewImpl {
        fun showProgressBar(visible: Int)
        fun showLinearLayout(visible: Int)
        fun emailError(error: String)
        fun passwordError(error: String)
        fun mainScreen()
        fun makeSnackbar(text: String)
    }

}