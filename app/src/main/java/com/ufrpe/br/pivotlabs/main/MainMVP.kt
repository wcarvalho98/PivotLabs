package com.ufrpe.br.pivotlabs.main

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
    }

    interface ViewImpl {

        fun function(function: String)
    }

}