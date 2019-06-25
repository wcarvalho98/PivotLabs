package com.ufrpe.br.pivotlabs.professional_select.professional_select.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP

class ProfessionalSelectActivity : AppCompatActivity(),ProfessionalSelectMVP.ViewImpl {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_select)
    }
}
