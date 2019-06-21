package com.ufrpe.br.pivotlabs.signup.model

import com.google.firebase.auth.FirebaseAuth
import com.ufrpe.br.pivotlabs.signup.SignUpMVP

class SignUpImpl(var presenter: SignUpMVP.PresenterImpl) : SignUpMVP.ModelImpl{
    private var user = FirebaseAuth.getInstance()

    override fun registerUser(userEmail: String, password: String) {
        user.createUserWithEmailAndPassword(userEmail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    presenter.showUserExistsActivity()
                } else {
                    presenter.makeSnackbar("Wasn't possible to add this user")
                    presenter.showUI(true)
                }
            }
    }

}