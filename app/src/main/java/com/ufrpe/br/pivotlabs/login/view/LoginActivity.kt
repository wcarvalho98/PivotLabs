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
    }

    override fun showProgressBar(visible: Int) {

    }

    override fun emailError() {
        tvEmail.requestFocus()
        tvEmail.error
    }

    override fun passwordError() {
        tvPassword.requestFocus()
        tvPassword.error
    }

    fun login() {
        presenter.login(tvEmail.text.toString(), tvPassword.text.toString())
    }
}
