package com.ufrpe.br.pivotlabs.professional_detail.view

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import kotlinx.android.synthetic.main.dialog_schedule_appointment.view.*

class ProfessionalDetailActivity : AppCompatActivity(),
                                   ProfessionalDetailMVP.ProfessionalDetailViewImpl,
    DayScheduleFragment.OnItemSelectedListener {

    /**
     * Activity destine to detailing the data of the professional as well as to
     * manage the interationc among fagments
     *
     */

    val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl = ProfessionalDetailPresenter()
    //Creating variables where the instances of the fragments will be kept
    lateinit var activityFragment : Fragment //Temporary fagment intance holder, the view will be switching it
    //Between DaySchedule Fragment and DayPeriod Fragment
    lateinit var appointmentFragment : Fragment // Variable destined to hold solely the appointment fragmen for
    //when the user chooses to go back do day period, only the appoitnment fragment shall be destroyed


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_detail)

        //Getting data passed from previos activity by put extra
        presenter.createDoctorObjectWithDataFromPreviousActivity(intent)
        presenter.setView(this)
        imgBtnBackToProfessionalSelect.setOnClickListener { onBackPressed() }
        imgBtnBackToMain.setOnClickListener { returnToMainActivity() }

        fillTextViewsWithDoctorData()//Auxiliary methods for displaying doctor data
        initializeDayScheduleFragment()//Initializing the daySchedule fragment

    }

    override fun fillTextViewsWithDoctorData() {
        val doctor = presenter.getDoctorObject()
        if(doctor.name != "" )
            tvProfessionalName.text = doctor.name
        if(doctor.speciality != null)
            tvSpeciality.text = doctor.speciality
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


    /**
     * When the user presses return to day period this procedure is called
     * and only the appoitnment fragment created in removed
     * */
    override fun returnFromAppointmentFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(appointmentFragment).commit()
    }

    /**
     * Initializing the first fragment
     * */
    override fun initializeDayScheduleFragment(){
        activityFragment = DayScheduleFragment(presenter,this)
        supportFragmentManager.beginTransaction().replace(R.id.flFragmentContent,activityFragment).commit()
    }

    /**
     * When the user chooses an appointment this method makes the view
     * show a dialog message warning that if the user doesn't show up
     * a tax will be charged on the user
     */
    override fun showDialog(){

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setCancelable(false)

        //var view = layoutInflater.inflate(R.layout.dialog_schedule_appointment,null)
        dialog.setContentView(R.layout.dialog_schedule_appointment)
        val btnDialogOk = dialog.findViewById<Button>(R.id.btnDialogOk)
        btnDialogOk.setOnClickListener{
            presenter.saveAppointmentInRemote()
            dialog.dismiss()
        }

        val btnDialogCancel = dialog.findViewById<Button>(R.id.btnDialogCancel)
        btnDialogCancel.setOnClickListener{
            presenter.cancelAppointmentSaving()
            dialog.dismiss()
        }
        dialog.show()
    }

    //Creating toasts for success of storing data
    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun showToast(message: Int) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
