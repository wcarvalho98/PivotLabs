package com.ufrpe.br.pivotlabs.professional_detail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.presenter.ProfessionalDetailPresenter

class ProfessionalDetailActivity : AppCompatActivity(), ProfessionalDetailMVP.ViewImpl {

    private var presenter: ProfessionalDetailMVP.PresenterImpl = ProfessionalDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_detail)
        presenter.setView(this)
    }
}
