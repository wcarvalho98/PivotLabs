package com.ufrpe.br.pivotlabs.signup

import android.content.Intent
import android.widget.EditText
import com.ufrpe.br.pivotlabs.signup.view.SignUpActivity

interface SignUpMVP{

    interface SignUpActivityImpl{
    }

    interface SignUpPresenterImpl{
        fun register(userEmail:String,password:String,confirmPassword:String):Boolean
        fun showUserExistsActivity(activity:SignUpActivity): Intent
    }

    interface SignUpModelImpl{
        fun userExists()
        fun registerUser()
    }
}