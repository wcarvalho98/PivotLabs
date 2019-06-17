package com.ufrpe.br.pivotlabs.main.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ufrpe.br.pivotlabs.Glide.GlideApp
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.main.MainMVP
import com.ufrpe.br.pivotlabs.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainMVP.ViewImpl {

    private val presenter: MainMVP.PresenterImpl = MainPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvUser.text = presenter.getUserName()
    }
}
