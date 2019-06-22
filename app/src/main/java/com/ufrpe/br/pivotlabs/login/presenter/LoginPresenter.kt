package com.ufrpe.br.pivotlabs.login.presenter

import android.content.Intent
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.ufrpe.br.pivotlabs.login.LoginMVP
import com.ufrpe.br.pivotlabs.login.model.LoginImpl
import com.ufrpe.br.pivotlabs.login.view.LoginActivity
import com.ufrpe.br.pivotlabs.login.view.UserExistActivity
import com.ufrpe.br.pivotlabs.signup.view.SignUpActivity

class LoginPresenter : LoginMVP.PresenterImpl {

    private var model: LoginMVP.ModelImpl = LoginImpl(this)
    private lateinit var loginView: LoginMVP.LoginActivityImpl
    private lateinit var userView: LoginMVP.UserExistActivityImpl

    override fun login(email: String, password: String) {
        showLoginUI(false)
        var error = false
        if (email.isBlank()) {
            loginView.emailError("Email required")
            error = true
        }
        if (!error && !email.contains("@")) {
            loginView.emailError("Invalid email entry")
            error = true
        }
        if (!error && password.isBlank()) {
            loginView.passwordError("Password required")
            error = true
        }
        if (!error && password.length < 6) {
            loginView.passwordError("Invalid password entry")
            error = true
        }
        if (error) {
            showLoginUI(true)
            return
        }
        model.login(email, password)
    }

    override fun setUserName(name: String) {
        showUserUI(false)
        if (name.isBlank()) {
            userView.userError("Invalid user name")
            showUserUI(true)
            return
        }
        model.setUserName(name)
    }

    override fun logout() {
        model.logout()
    }

    override fun setView(view: LoginActivity) {
        this.loginView = view
    }

    override fun setView(view: UserExistActivity) {
        this.userView = view
    }

    override fun getUser(): FirebaseAuth {
        return model.getUser()
    }

    override fun loginMainScreen() {
        loginView.mainScreen()
    }

    override fun userMainScreen() {
        userView.mainScreen()
    }

    override fun makeLoginSnackbar(text: String) {
        loginView.makeSnackbar(text)
    }

    override fun makeUserSnackbar(text: String) {
        userView.makeSnackbar(text)
    }

    override fun showLoginUI(status: Boolean) {
        var visible = if (!status) View.VISIBLE else View.GONE
        loginView.showProgressBar(visible)
        visible = if (status) View.VISIBLE else View.INVISIBLE
        loginView.showLinearLayout(visible)
    }

    override fun showUserUI(status: Boolean) {
        var visible = if (!status) View.VISIBLE else View.GONE
        userView.showProgressBar(visible)
        visible = if (status) View.VISIBLE else View.INVISIBLE
        userView.showLinearLayout(visible)
    }

    override fun showUserScreen() {
        loginView.userExistScreen()
    }

    override fun signUp(activity: LoginActivity):Intent{
        return Intent(activity,SignUpActivity::class.java)
    }

}