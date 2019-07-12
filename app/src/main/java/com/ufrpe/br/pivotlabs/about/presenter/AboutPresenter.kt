package com.ufrpe.br.pivotlabs.about.presenter

import android.app.Activity
import android.content.Intent
import com.ufrpe.br.pivotlabs.about.AboutMVP
import com.ufrpe.br.pivotlabs.main.view.MainActivity

class AboutPresenter: AboutMVP.AboutPresenterImpl {

    override fun getMainActivityIntent(act: Activity): Intent {

        return Intent(act,MainActivity::class.java)
    }
}