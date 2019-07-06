package com.ufrpe.br.pivotlabs.signup.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.beans.Patient
import com.ufrpe.br.pivotlabs.signup.SignUpMVP

class SignUpImpl(var presenter: SignUpMVP.PresenterImpl) : SignUpMVP.ModelImpl{
    private var user = FirebaseAuth.getInstance()
    private var patientRef = FirebaseDatabase.getInstance().getReference("patient")

    override fun registerUser(userEmail: String, password: String) {
        user.createUserWithEmailAndPassword(userEmail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val patient = Patient(userEmail, null, null, null, null)
                    patientRef.child(user.currentUser!!.uid).setValue(patient)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                presenter.showUserExistsActivity()
                            } else {
                                presenter.makeSnackbar("Wasn't possible to add this user")
                                user.currentUser!!.delete()
                                    .addOnCompleteListener { delete ->
                                        if (delete.isSuccessful) {
                                            presenter.showUI(true)
                                        } else {
                                            presenter.makeSnackbar("We are with some error on our operation")
                                            presenter.showUI(true)
                                        }
                                    }
                            }
                        }
                } else {
                    presenter.makeSnackbar("Wasn't possible to add this user")
                    presenter.showUI(true)
                }
            }
    }

}