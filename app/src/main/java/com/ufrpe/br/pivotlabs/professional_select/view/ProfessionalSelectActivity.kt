package com.ufrpe.br.pivotlabs.professional_select.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP
import com.ufrpe.br.pivotlabs.professional_select.presenter.ProfessionalSelectPresenter
import kotlinx.android.synthetic.main.activity_professional_select.*

class ProfessionalSelectActivity : AppCompatActivity(),ProfessionalSelectMVP.ViewImpl {

    private var presenter: ProfessionalSelectMVP.PresenterImpl = ProfessionalSelectPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_select)
        btnHome.setOnClickListener { returnToMainActivity() }
        setSpinnerOptions()
    }


    /** Set the options for each dropdown to be used as specialty filter
     * */
    private fun setSpinnerOptions(){

        var specialtySpinner = spnSpecialty
        ArrayAdapter.createFromResource(this,
            R.array.specialty,
            R.layout.text_spinner).also { adapter->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                specialtySpinner.adapter = adapter
            }
        var citySpinner = spnCity
        ArrayAdapter.createFromResource(this,
            R.array.city_names,
            R.layout.text_spinner).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            citySpinner.adapter = adapter
        }


        var dayPeriodSpinner = spnDayPeroid
        ArrayAdapter.createFromResource(this,
            R.array.day_periods,
            R.layout.text_spinner).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dayPeriodSpinner.adapter = adapter
        }
    }


    override fun returnToMainActivity() {
        var intent = presenter.getHomeIntent(this)
        startActivity(intent)
        this.finish()
    }

    /**
     * Requests to the presenter a list of professionals with the given filters
     * and creates a new array adapter with the list of professionals updated
     * and displays it
     * */
    override fun fetchFilteredProfessionals() {
        presenter.evaluateFetchfilteredProfetionals(spnSpecialty.selectedItem.toString(),
                                                    spnCity.selectedItem.toString(),
                                                    spnDayPeroid.selectedItem.toString())
    }


}
