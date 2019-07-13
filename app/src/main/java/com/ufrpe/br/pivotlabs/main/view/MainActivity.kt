package com.ufrpe.br.pivotlabs.main.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        imgBtnPeople.setOnClickListener {
            displayAboutActivity()
        }
        btnSchedule.setOnClickListener {
            displayProfessionalSelectActivity()
        }
        presenter.setView(this)
        presenter.isDoctor()
    }

    override fun function(function: String) {
        tvFunction.text = function
    }

    override fun displayAboutActivity() {
        var intent = presenter.getAboutActivityIntent(this)
        this.startActivity(intent)
        this.finish()
    }

    override fun displayProfessionalSelectActivity(){
        var intent = presenter.getProfessionalSelectActivityIntent(this)
        this.startActivity(intent)
        this.finish()
    }

}
