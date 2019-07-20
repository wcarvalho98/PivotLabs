package com.ufrpe.br.pivotlabs.professional_detail.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.presenter.ProfessionalDetailPresenter
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

import kotlinx.android.synthetic.main.activity_professional_detail.*

class ProfessionalDetailActivity : AppCompatActivity(),
                                   ProfessionalDetailMVP.ProfessionalDetailViewImpl,DayScheduleFragment.OnItemSelectedListener {


    val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl = ProfessionalDetailPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_detail)
        presenter.createDoctorObjectWithDataFromPreviousActivity(getIntent())
        presenter.setView(this)
        imgBtnBackToProfessionalSelect.setOnClickListener { returnToProfessionalSelectActivity() }
        imgBtnBackToMain.setOnClickListener { returnToMainActivity() }
        fillTextViewsWithDoctorData()

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

    override fun refreshScheduleList(scheduleList: ArrayList<DaySchedule>) {
        //rvSchedules.adapter = ScheduleAdapter(scheduleList,this)
    }

    //This is called when a schedule has been selected from the schedules  list
    override fun onItemSelected(ds: DaySchedule) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
