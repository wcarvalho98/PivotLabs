package com.ufrpe.br.pivotlabs.signup.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.signup.SignUpMVP
import com.ufrpe.br.pivotlabs.signup.presenter.SignUpPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), SignUpMVP.SignUpActivityImpl {

    private var presenter : SignUpMVP.SignUpPresenterImpl = SignUpPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btnClear.setOnClickListener{clear()}
        btnRegister.setOnClickListener{register()}
    }

    private fun clear(){
        edtPassword.setText("")
        edtConfirmPassword.setText("")
        edtUserEmail.setText("")
    }

    private fun register(){
        var b = presenter.register(edtUserEmail.toString(),edtPassword.toString(),edtConfirmPassword.toString())
        if(b) {
            var intent = presenter.showUserExistsActivity(this)
            startActivity(intent)
            this.finish()
        }
    }

}
