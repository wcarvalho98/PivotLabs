package com.ufrpe.br.pivotlabs.professional_select

import android.content.Intent
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

interface ProfessionalSelectMVP{

    interface ModelImpl{

    }

    interface PresenterImpl{
        fun getHomeIntent(activity: ProfessionalSelectActivity):Intent
    }

    interface ViewImpl{
        fun returnToMainActivity()
    }
}