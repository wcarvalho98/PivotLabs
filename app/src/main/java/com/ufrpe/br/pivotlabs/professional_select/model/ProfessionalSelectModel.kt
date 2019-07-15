package com.ufrpe.br.pivotlabs.professional_select.model

import android.os.AsyncTask
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP

class ProfessionalSelectModel(var presenter: ProfessionalSelectMVP.PresenterImpl) : ProfessionalSelectMVP.ModelImpl{

    override fun fetchAllprofessionals(): ArrayList<Doctor> = RequestProfessionalsFromRemote().execute().get()

    class RequestProfessionalsFromRemote : AsyncTask<Void,Void,ArrayList<Doctor>>(){

        override fun doInBackground(vararg params: Void?): ArrayList<Doctor>? {
            val values = FirebaseDatabase.getInstance().getReference("doctors")
            val docs = ArrayList<Doctor>(5)
            values.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    for (child in p0.children) {
                        docs.add(child.getValue(Doctor::class.java)!!)
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
            return docs
        }
    }
}