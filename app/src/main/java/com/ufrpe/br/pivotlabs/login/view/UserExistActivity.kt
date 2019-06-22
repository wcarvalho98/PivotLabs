package com.ufrpe.br.pivotlabs.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.login.LoginMVP
import com.ufrpe.br.pivotlabs.login.presenter.LoginPresenter
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_user_exist.*

class UserExistActivity : AppCompatActivity(), LoginMVP.UserExistActivityImpl {

    private val presenter: LoginMVP.PresenterImpl = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_exist)

        presenter.setView(this)
        btSetUser.setOnClickListener { setUserName() }
    }

    override fun showProgressBar(visible: Int) {
        pbLoading2.visibility = visible
    }

    override fun showLinearLayout(visible: Int) {
        llContent.visibility = visible
    }

    override fun userError(error: String) {
        tvUserName.requestFocus()
    }

    override fun mainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun makeSnackbar(text: String) {
        val snack = Snackbar.make(this.textView3, text, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(getColor(R.color.white))
        snack.show()
    }

    private fun setUserName() {
        presenter.setUserName(tvUserName.text.toString())
    }

}
