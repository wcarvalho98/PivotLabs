package com.ufrpe.br.pivotlabs.professional_detail.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.presenter.ProfessionalDetailPresenter
import com.ufrpe.br.pivotlabs.professional_detail.view.day_schedule.DayScheduleFragment
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

import kotlinx.android.synthetic.main.activity_professional_detail.*

class ProfessionalDetailActivity : AppCompatActivity(),
                                   ProfessionalDetailMVP.ProfessionalDetailViewImpl,
    DayScheduleFragment.OnItemSelectedListener {


    lateinit var dayScheduleFragment : DayScheduleFragment
    val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl = ProfessionalDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_detail)
        presenter.createDoctorObjectWithDataFromPreviousActivity(getIntent())
        presenter.setView(this)
        imgBtnBackToProfessionalSelect.setOnClickListener { returnToProfessionalSelectActivity() }
        imgBtnBackToMain.setOnClickListener { returnToMainActivity() }
        fillTextViewsWithDoctorData()
        initializeDayScheduleFragment()

    }

    override fun fillTextViewsWithDoctorData() {
        var doctor = presenter.getDoctorObject()
        if(doctor.name != "" )
            tvProfessionalName.text = doctor.name
        if(doctor.speciality != null)
            tvSpeciality.text = doctor.speciality
    }


    override fun returnToProfessionalSelectActivity() {
        var intent = Intent(this,ProfessionalSelectActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun returnToMainActivity() {
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    //This is called when a schedule has been selected from the schedules  list
    override fun onItemSelected(ds: DaySchedule) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun initializeDayScheduleFragment(){
        dayScheduleFragment =
            DayScheduleFragment(presenter)
        supportFragmentManager.beginTransaction().replace(R.id.flFragmentContent,dayScheduleFragment).commit()
    }

}
