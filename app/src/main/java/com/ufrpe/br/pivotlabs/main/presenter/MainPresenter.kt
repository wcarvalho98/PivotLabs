package com.ufrpe.br.pivotlabs.main.presenter

import com.ufrpe.br.pivotlabs.main.MainMVP
import com.ufrpe.br.pivotlabs.main.model.MainImpl
import com.ufrpe.br.pivotlabs.main.view.MainActivity

class MainPresenter : MainMVP.PresenterImpl {
    private var model: MainMVP.ModelImpl = MainImpl(this)
    lateinit var view: MainMVP.ViewImpl

    override fun getUserName(): String {
        return model.getUserName()!!
    }

    override fun setView(view: MainActivity) {
        this.view = view
    }

}