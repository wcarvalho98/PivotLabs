package com.ufrpe.br.pivotlabs.login.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.ufrpe.br.pivotlabs.login.LoginMVP

class LoginImpl(var presenter: LoginMVP.PresenterImpl) : LoginMVP.ModelImpl {
    private lateinit var user: FirebaseAuth

    override fun login(email: String, password: String) {
        user = FirebaseAuth.getInstance()
        user.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                presenter.showLoginUI(true)
                if (task.isSuccessful) {
                    if (user.currentUser?.displayName == null) {
                        presenter.showUserScreen()
                    } else {
                        presenter.loginMainScreen()
                    }
                } else {
                    presenter.makeLoginSnackbar("User doesn't exist")
                }
            }
    }

    override fun setUserName(name: String) {
        user = FirebaseAuth.getInstance()
        val us = UserProfileChangeRequest.Builder()
        us.setDisplayName(name)
        user.currentUser?.updateProfile(us.build())
            ?.addOnCompleteListener { task ->
            presenter.showUserUI(true)
            if (task.isSuccessful) {
                presenter.userMainScreen()
            } else {
                presenter.makeUserSnackbar("Wasn't possible to set user name")
            }
        }
    }

    override fun logout() {
        presenter.logout()
    }

    override fun getUser(): FirebaseAuth {
        return this.user
    }

}