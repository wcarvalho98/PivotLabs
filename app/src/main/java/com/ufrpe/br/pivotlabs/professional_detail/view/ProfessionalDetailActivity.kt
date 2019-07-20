package com.ufrpe.br.pivotlabs.professional_detail.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DayPeriod
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.beans.IndentifiedAppointment
import com.ufrpe.br.pivotlabs.main.view.MainActivity
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.presenter.ProfessionalDetailPresenter
import com.ufrpe.br.pivotlabs.professional_detail.view.appointment.AppointmentFragment
import com.ufrpe.br.pivotlabs.professional_detail.view.day_period.DayPeriodFragment
import com.ufrpe.br.pivotlabs.professional_detail.view.day_schedule.DayScheduleFragment
import com.ufrpe.br.pivotlabs.professional_select.view.ProfessionalSelectActivity

import kotlinx.android.synthetic.main.activity_professional_detail.*

class ProfessionalDetailActivity : AppCompatActivity(),
                                   ProfessionalDetailMVP.ProfessionalDetailViewImpl,
    DayScheduleFragment.OnItemSelectedListener {



    val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl = ProfessionalDetailPresenter()
    lateinit var activityFragment : Fragment
    lateinit var appointmentFragment : Fragment


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

    // FRAGMETS RELATED Procedures

    //This is called when a schedule has been selected from the schedules  list
    override fun onItemSelected(ds: DaySchedule) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun switchToDayPeriodFragment(dayPeriod: ArrayList<DayPeriod>) {
        val newFragment = DayPeriodFragment(dayPeriod,presenter,this)
        val transaction  = supportFragmentManager.beginTransaction()
        transaction.remove(activityFragment)
        transaction.add(R.id.flFragmentContent,newFragment,null).commit()
        //transaction.replace(R.id.flFragmentContent,newFragment).commit()
        activityFragment = newFragment
    }

    override fun switchToAppointmentFragment(appointment: ArrayList<IndentifiedAppointment>) {
        val newFragment = AppointmentFragment(appointment,presenter,this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.flFragmentContent,newFragment,null).commit()
        appointmentFragment = newFragment
    }


    override fun returnFromAppointmentFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(appointmentFragment).commit()
    }

    override fun initializeDayScheduleFragment(){
        activityFragment = DayScheduleFragment(presenter,this)
        supportFragmentManager.beginTransaction().replace(R.id.flFragmentContent,activityFragment).commit()
    }

}
