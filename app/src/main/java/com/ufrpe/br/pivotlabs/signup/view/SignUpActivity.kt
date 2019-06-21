package com.ufrpe.br.pivotlabs.signup.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.login.view.UserExistActivity
import com.ufrpe.br.pivotlabs.signup.SignUpMVP
import com.ufrpe.br.pivotlabs.signup.presenter.SignUpPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), SignUpMVP.ViewImpl {

    private var presenter : SignUpMVP.PresenterImpl = SignUpPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        btnClear.setOnClickListener{clear()}
        btnRegister.setOnClickListener{ register() }
        presenter.setView(this)
    }

    override fun showUserExistsActivity(){
        val intent = Intent(this, UserExistActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun showLinearLayout(visible: Int) {
        llSignUp.visibility = visible
    }

    override fun showProgressBar(visible: Int) {
        pbLoading3.visibility = visible
    }

    override fun emailError(error: String) {
        edtUserEmail.requestFocus()
        edtUserEmail.error = error
    }

    override fun passwordError(error: String) {
        edtPassword.requestFocus()
        edtPassword.error = error
    }

    override fun confirmPasswordError(error: String) {
        edtConfirmPassword.requestFocus()
        edtConfirmPassword.error = error
    }

    override fun makeSnackbar(text: String) {
        val snack = Snackbar.make(clRoot, text, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(getColor(R.color.white))
        snack.show()
    }

    private fun clear(){
        edtPassword.setText("")
        edtConfirmPassword.setText("")
        edtUserEmail.setText("")
    }

    private fun register() {
        presenter.register(edtUserEmail.text.toString(), edtPassword.text.toString(), edtConfirmPassword.text.toString())
    }

}
