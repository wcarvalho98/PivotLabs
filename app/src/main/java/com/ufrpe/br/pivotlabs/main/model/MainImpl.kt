package com.ufrpe.br.pivotlabs.main.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.main.MainMVP

class MainImpl(var presenter: MainMVP.PresenterImpl) : MainMVP.ModelImpl {
    private lateinit var user: FirebaseAuth

    override fun getUserName(): String? {
        user = FirebaseAuth.getInstance()
        return user.currentUser?.displayName
    }

    override fun isDoctor() {
        user = FirebaseAuth.getInstance()
        val doctorRef = FirebaseDatabase.getInstance().getReference("doctor").child(user.currentUser!!.uid)
        doctorRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(Doctor::class.java)
                if (value != null) {
                    if (value.speciality != null)
                        presenter.setFunction(value.speciality.toString())
                    else
                        presenter.setFunction("Profissional")
                } else {
                    presenter.setFunction("Paciente")
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

}