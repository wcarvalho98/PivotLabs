package com.ufrpe.br.pivotlabs.signup.presenter


import android.content.Intent
import com.ufrpe.br.pivotlabs.login.view.UserExistActivity
import com.ufrpe.br.pivotlabs.signup.SignUpMVP
import com.ufrpe.br.pivotlabs.signup.view.SignUpActivity

class SignUpPresenter : SignUpMVP.SignUpPresenterImpl{



    override fun register(userEmail: String, password: String, confirmPassword: String):Boolean{
        //TODO(reason = "Verify if the user already exist in the database")
        return true
    }

    override fun showUserExistsActivity(activity: SignUpActivity) :Intent{
        return  Intent(activity,UserExistActivity::class.java)
    }

}