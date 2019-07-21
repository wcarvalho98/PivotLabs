package com.ufrpe.br.pivotlabs.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.animator.AnimatorView
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
        AnimatorView.fade(tvFunction, true)
    }

    override fun displayAboutActivity() {
        val intent = presenter.getAboutActivityIntent(this)
        this.startActivity(intent)
    }

    override fun displayProfessionalSelectActivity(){
        val intent = presenter.getProfessionalSelectActivityIntent(this)
        this.startActivity(intent)
    }

}
