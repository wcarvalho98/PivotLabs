package com.ufrpe.br.pivotlabs.professional_detail.model

import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP

class ProfessionalDetailModel(var presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl) : ProfessionalDetailMVP.ProfessionalDetailModelImpl{

    override fun fetchAllSchedules(): ArrayList<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}