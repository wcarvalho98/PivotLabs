package com.ufrpe.br.pivotlabs.professional_select.model

import android.os.AsyncTask
import com.google.firebase.database.FirebaseDatabase
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP

class ProfessionalSelectModel : ProfessionalSelectMVP.ModelImpl{

    override fun fetchAllprofessionals() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class RequestProfessionalsFromRemote : AsyncTask<Void,Void,ArrayList<Doctor>>(){

        var doctors = FirebaseDatabase.getInstance()

        override fun doInBackground(vararg params: Void?): ArrayList<Doctor>? {
            var values = doctors.getReference("doctors")
            var docs = ArrayList<String>(5)

            return null
        }
    }
}