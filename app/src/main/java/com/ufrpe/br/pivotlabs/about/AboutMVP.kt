package com.ufrpe.br.pivotlabs.about

import android.app.Activity
import android.content.Intent

interface AboutMVP {

    interface AboutViewImpl{
        fun displayMainActivity()
    }

    interface AboutPresenterImpl{
        fun getMainActivityIntent(act : Activity): Intent
    }

    interface AboutModelmpl {

    }

}