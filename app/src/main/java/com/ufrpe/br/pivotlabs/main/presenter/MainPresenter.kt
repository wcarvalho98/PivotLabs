package com.ufrpe.br.pivotlabs.main.presenter

import android.app.Activity
import android.content.Intent
import com.ufrpe.br.pivotlabs.about.view.AboutActivity
import com.ufrpe.br.pivotlabs.main.MainMVP
import com.ufrpe.br.pivotlabs.main.model.MainImpl
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

class MainPresenter : MainMVP.PresenterImpl {

    private var model: MainMVP.ModelImpl = MainImpl(this)
    lateinit var view: MainMVP.ViewImpl

    override fun getUserName(): String {
        return model.getUserName()!!
    }

    override fun setView(view: MainActivity) {
        this.view = view
    }

    override fun isDoctor() {
        model.isDoctor()
    }

    override fun setFunction(function: String) {
        view.function(function)
    }

    override fun getAboutActivityIntent(act: Activity): Intent {
        return Intent(act,AboutActivity::class.java)
    }

    override fun getProfessionalSelectActivityIntent(act: Activity): Intent {
        return Intent(act,ProfessionalSelectActivity::class.java)
    }

}