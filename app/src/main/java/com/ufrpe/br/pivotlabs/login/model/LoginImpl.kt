package com.ufrpe.br.pivotlabs.login.model

import com.ufrpe.br.pivotlabs.login.LoginMVP

class LoginImpl(var presenter: LoginMVP.PresenterImpl) : LoginMVP.ModelImpl {
    private lateinit var email: String

    override fun login(email: String, password: String) {
        this.email = email
        presenter.login(email, password)
    }

    override fun logout() {
        presenter.logout()
    }

    override fun getEmail(): String {
        return this.email
    }

}