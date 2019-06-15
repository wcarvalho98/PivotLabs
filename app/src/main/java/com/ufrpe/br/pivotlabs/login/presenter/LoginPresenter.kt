package com.ufrpe.br.pivotlabs.login.presenter

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.ufrpe.br.pivotlabs.login.LoginMVP
import com.ufrpe.br.pivotlabs.login.model.LoginImpl

class LoginPresenter : LoginMVP.PresenterImpl {
    private var model: LoginMVP.ModelImpl = LoginImpl(this)
    private lateinit var view: LoginMVP.ViewImpl

    override fun login(email: String, password: String) {
        showUI(false)
        var error = false
        if (email.isBlank()) {
            view.emailError("Email required")
            error = true
        }
        if (!error && !email.contains("@")) {
            view.emailError("Invalid email entry")
            error = true
        }
        if (!error && password.isBlank()) {
            view.passwordError("Password required")
            error = true
        }
        if (!error && password.length < 4) {
            view.passwordError("Invalid password entry")
            error = true
        }
        if (error) {
            showUI(true)
            return
        }
        model.login(email, password)
    }

    override fun setUserName(name: String) {
        showUI(false)
        if (name.isBlank()) {
            showUI(true)
            return
        }
    }

    override fun logout() {
        model.logout()
    }

    override fun setView(view: LoginMVP.ViewImpl) {
        this.view = view
    }

    override fun getUser(): FirebaseAuth {
        return model.getUser()
    }

    override fun showUI(status: Boolean) {
        var visible = if (!status) View.VISIBLE else View.GONE
        view.showProgressBar(visible)
        visible = if (status) View.VISIBLE else View.INVISIBLE
        view.showLinearLayout(visible)
    }

}