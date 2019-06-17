package com.ufrpe.br.pivotlabs.signup.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.signup.SignUpMVP
class SignUpActivity : AppCompatActivity(), SignUpMVP.SignUpActivityImpl {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    override fun setUsername(username: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEmail(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPassword(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun usernameError(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
