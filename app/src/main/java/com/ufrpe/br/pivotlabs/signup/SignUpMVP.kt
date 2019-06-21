package com.ufrpe.br.pivotlabs.signup

import com.ufrpe.br.pivotlabs.signup.view.SignUpActivity

interface SignUpMVP{

    interface ModelImpl{
        fun registerUser(userEmail: String, password: String)
        fun registerProfessional(userEmail: String, password: String)
    }

    interface PresenterImpl{
        fun register(userEmail: String, password: String, confirmPassword: String)
        fun showUserExistsActivity()
        fun showUI(status: Boolean)
        fun makeSnackbar(text: String)
        fun setView(view: SignUpActivity)
    }

    interface ViewImpl{
        fun showProgressBar(visible: Int)
        fun showLinearLayout(visible: Int)
        fun showUserExistsActivity()
        fun emailError(error: String)
        fun passwordError(error: String)
        fun confirmPasswordError(error: String)
        fun makeSnackbar(text: String)
        fun isPatient(): Boolean
    }
}