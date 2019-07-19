package com.ufrpe.br.pivotlabs.professional_detail.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP

import kotlinx.android.synthetic.main.activity_professional_detail.*

class ProfessionalDetailActivity : AppCompatActivity(), ProfessionalDetailMVP.ProfessionalDetailViewImpl {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_detail)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun returnToProfessionalSelectActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun returnToMainActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
