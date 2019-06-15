package com.ufrpe.br.pivotlabs.login.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.CharSequenceTransformation
import android.support.design.widget.Snackbar
import android.view.Gravity
import android.view.View
import android.view.View.*
import com.squareup.picasso.Picasso
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.login.LoginMVP
import com.ufrpe.br.pivotlabs.login.LoginMVP.PresenterImpl
import com.ufrpe.br.pivotlabs.login.presenter.LoginPresenter
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.tvEmail

class LoginActivity : AppCompatActivity(), LoginMVP.ViewImpl {

    private val presenter: PresenterImpl = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.setView(this)
        btLogin.setOnClickListener { login() }

    }

    override fun showProgressBar(visible: Int) {
        pbLoading.visibility = visible
    }

    override fun showLinearLayout(visible: Int) {
        llContent.visibility = visible
    }

    override fun emailError(error: String) {
        tvEmail.requestFocus()
        tvEmail.error = error
    }

    override fun passwordError(error: String) {
        tvPassword.requestFocus()
        tvPassword.error = error
    }

    override fun mainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun makeSnackbar(text: String) {
        var snack = Snackbar.make(this.textView3, text, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(getColor(R.color.white))
        snack.show()
    }

    private fun login() {
        presenter.login(tvEmail.text.toString(), tvPassword.text.toString())
    }
}
