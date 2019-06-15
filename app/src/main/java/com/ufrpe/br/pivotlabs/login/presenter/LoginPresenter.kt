package com.ufrpe.br.pivotlabs.login.presenter

import android.view.View
import com.ufrpe.br.pivotlabs.login.LoginMVP
import com.ufrpe.br.pivotlabs.login.model.LoginImpl

class LoginPresenter : LoginMVP.PresenterImpl {
    private var model: LoginMVP.ModelImpl = LoginImpl(this)
    private lateinit var view: LoginMVP.ViewImpl

    override fun login(email: String, password: String) {
        if (email.isBlank()) {
            view.emailError()
            return
        }
        if (password.isBlank()) {
            view.passwordError()
            return
        }
        model.login(email, password)
    }

    override fun logout() {
        model.logout()
    }

    override fun setView(view: LoginMVP.ViewImpl) {
        this.view = view
    }

    override fun getEmail(): String {
        return model.getEmail()
    }

    override fun showProgressBar(status: Boolean) {
        var visible = if (status) View.VISIBLE else View.GONE
        view.showProgressBar(visible)
    }

}