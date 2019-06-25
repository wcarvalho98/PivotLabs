package com.ufrpe.br.pivotlabs.professional_select.model

import android.os.AsyncTask
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP

class ProfessionalSelectModel : ProfessionalSelectMVP.ModelImpl{

    override fun fetchAllprofessionals() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class RequestProfessionalsFromRemote : AsyncTask<Void,Void,Doctor>(){

        override fun doInBackground(vararg params: Void?): Doctor? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}