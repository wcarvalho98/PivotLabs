package com.ufrpe.br.pivotlabs.main.model

import com.google.firebase.auth.FirebaseAuth
import com.ufrpe.br.pivotlabs.main.MainMVP

class MainImpl(var presenter: MainMVP.PresenterImpl) : MainMVP.ModelImpl {
    private lateinit var user: FirebaseAuth

    override fun getUserName(): String? {
        user = FirebaseAuth.getInstance()
        return user.currentUser?.displayName
    }



}