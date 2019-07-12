package com.ufrpe.br.pivotlabs.about.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.about.AboutMVP
import com.ufrpe.br.pivotlabs.about.presenter.AboutPresenter
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity :AboutMVP.AboutViewImpl, AppCompatActivity() {

    private var presenter : AboutMVP.AboutPresenterImpl = AboutPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        btnPaciente.setOnClickListener {
            displayMainActivity()
        }
    }

    override fun displayMainActivity() {
        var intent = presenter.getMainActivityIntent(this)
        this.startActivity(intent)
        this.finish()
    }

}
