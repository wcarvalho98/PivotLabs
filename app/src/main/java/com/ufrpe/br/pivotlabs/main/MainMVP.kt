package com.ufrpe.br.pivotlabs.main

import android.app.Activity
import android.content.Intent
import com.ufrpe.br.pivotlabs.main.view.MainActivity

interface MainMVP {

    interface ModelImpl {
        fun getUserName(): String?
        fun isDoctor()
    }

    interface PresenterImpl {

        fun setView(view: MainActivity)
        fun getUserName(): String
        fun isDoctor()
        fun setFunction(function: String)
        fun getAboutActivityIntent(act: Activity): Intent
        fun getProfessionalSelectActivityIntent(act: Activity): Intent
    }

    interface ViewImpl {

        fun function(function: String)
        fun displayAboutActivity()
        fun diplayProfessionalSelectActivity()
    }

}