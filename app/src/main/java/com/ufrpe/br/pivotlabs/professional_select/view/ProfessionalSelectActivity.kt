package com.ufrpe.br.pivotlabs.professional_select.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_select.ProfessionalSelectMVP
import com.ufrpe.br.pivotlabs.professional_select.presenter.ProfessionalSelectPresenter
import kotlinx.android.synthetic.main.activity_professional_select.*

class ProfessionalSelectActivity : AppCompatActivity(), ProfessionalSelectMVP.ViewImpl {

    private var presenter: ProfessionalSelectMVP.PresenterImpl = ProfessionalSelectPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_select)
        btnHome.setOnClickListener { returnToMainActivity() }
        setSpinnerOptions()
        presenter.setView(this)
        rvDoctors.layoutManager = LinearLayoutManager(this)
        btnSearch.setOnClickListener { fetchFilteredProfessionals() }
    }

    /** Set the options for each dropdown to be used as speciality filter
     * */
    private fun setSpinnerOptions(){

        val specialitySpinner = spnSpeciality
        ArrayAdapter.createFromResource(this,
            R.array.speciality,
            R.layout.text_spinner).also { adapter->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                specialitySpinner.adapter = adapter
            }
        val citySpinner = spnCity
        ArrayAdapter.createFromResource(this,
            R.array.city_names,
            R.layout.text_spinner).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            citySpinner.adapter = adapter
        }


        val dayPeriodSpinner = spnDayPeroid
        ArrayAdapter.createFromResource(this,
            R.array.day_periods,
            R.layout.text_spinner).also { adapter->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dayPeriodSpinner.adapter = adapter
        }
    }

    override fun returnToMainActivity() {
        onBackPressed()
    }

    /**
     * Requests to the presenter a list of professionals with the given filters
     * and creates a new array adapter with the list of professionals updated
     * and displays it
     * */
    override fun fetchFilteredProfessionals() {
        presenter.evaluateFetchfilteredProfetionals(spnSpeciality.selectedItem.toString())
    }

    override fun refreshDoctors(doctorKeys:ArrayList<String>,doctors: ArrayList<Doctor>) {
        rvDoctors.adapter = ProfessionalAdapter(doctorKeys,doctors, this)
    }
}
