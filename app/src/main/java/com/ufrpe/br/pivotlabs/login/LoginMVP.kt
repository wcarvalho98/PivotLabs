package com.ufrpe.br.pivotlabs.login

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.ufrpe.br.pivotlabs.login.view.LoginActivity
import com.ufrpe.br.pivotlabs.login.view.UserExistActivity

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
        fun setView(view: LoginActivity)
        fun setView(view: UserExistActivity)
        fun makeLoginSnackbar(text: String)
        fun loginMainScreen()
        fun userMainScreen()
        fun makeUserSnackbar(text: String)
        fun showLoginUI(status: Boolean)
        fun showUserUI(status: Boolean)
        fun showUserScreen()
        fun signUp(activity: LoginActivity):Intent
    }

    interface LoginActivityImpl {
        fun showProgressBar(visible: Int)
        fun showLinearLayout(visible: Int)
        fun mainScreen()
        fun signUp()
        fun makeSnackbar(text: String)
        fun emailError(error: String)
        fun passwordError(error: String)
        fun userExistScreen()
    }

    interface UserExistActivityImpl {
        fun showProgressBar(visible: Int)
        fun showLinearLayout(visible: Int)
        fun mainScreen()
        fun makeSnackbar(text: String)
        fun userError(error: String)
    }

}