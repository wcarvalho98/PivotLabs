package com.ufrpe.br.pivotlabs.signup.presenter

import android.view.View
import com.ufrpe.br.pivotlabs.signup.SignUpMVP
import com.ufrpe.br.pivotlabs.signup.model.SignUpImpl
import com.ufrpe.br.pivotlabs.signup.view.SignUpActivity

class SignUpPresenter : SignUpMVP.PresenterImpl{

    private var model: SignUpMVP.ModelImpl = SignUpImpl(this)
    private lateinit var view: SignUpMVP.ViewImpl

    override fun setView(view: SignUpActivity) {
        this.view = view
    }

    override fun register(userEmail: String, password: String, confirmPassword: String){
        showUI(false)
        var error = false
        if (userEmail.isBlank()) {
            view.emailError("Email required")
            error = true
        }
        if (!error && !userEmail.contains("@")) {
            view.emailError("Email must have an '@' character")
            error = true
        }
        if (!error && password.isBlank()) {
            view.passwordError("Password required")
            error = true
        }
        if (!error && password.length < 6) {
            view.passwordError("Password must have more than 5 digits")
            error = true
        }
        if (!error && confirmPassword != password) {
            view.confirmPasswordError("Passwords doesn't match")
            error = true
        }
        if (error) {
            showUI(true)
            return
        }
        model.registerUser(userEmail, password)
    }

    override fun showUserExistsActivity() {
        view.showUserExistsActivity()
    }

    override fun makeSnackbar(text: String) {
        view.makeSnackbar(text)
    }

    override fun showUI(status: Boolean) {
        var visible = if (!status) View.VISIBLE else View.GONE
        view.showProgressBar(visible)
        visible = if (status) View.VISIBLE else View.INVISIBLE
        view.showLinearLayout(visible)
    }

}