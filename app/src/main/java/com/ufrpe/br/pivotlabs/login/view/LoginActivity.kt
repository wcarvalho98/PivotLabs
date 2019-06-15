package com.ufrpe.br.pivotlabs.login.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.login.LoginMVP
import com.ufrpe.br.pivotlabs.login.LoginMVP.PresenterImpl
import com.ufrpe.br.pivotlabs.login.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
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

    fun login() {
        presenter.login(tvEmail.text.toString(), tvPassword.text.toString())
    }
}
