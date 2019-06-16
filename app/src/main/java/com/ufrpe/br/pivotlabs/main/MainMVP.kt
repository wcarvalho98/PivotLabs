package com.ufrpe.br.pivotlabs.main

import com.ufrpe.br.pivotlabs.main.view.MainActivity

interface MainMVP {

    interface ModelImpl {
        fun getUserName(): String?
    }

    interface PresenterImpl {

        fun setView(view: MainActivity)
        fun getUserName(): String
    }

    interface ViewImpl {

    }

}