package com.ufrpe.br.pivotlabs.professional_detail.view.appointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.Appointment
import com.ufrpe.br.pivotlabs.beans.IndentifiedAppointment
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlinx.android.synthetic.main.fragment_appointment.view.*

class AppointmentFragment (var listAppointmets : ArrayList<IndentifiedAppointment>,
                           val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl,
                           val view : ProfessionalDetailMVP.ProfessionalDetailViewImpl):
        Fragment(), ProfessionalDetailMVP.ProfessionalDetailViewImpl.AppointmentFragmentImpl{
    /**
     * This Fragment controls the displaying and selection of the IndentifiedAppointments list
     */
    //Variables destined to hold the references of the view
    lateinit var imgBtnBackToDayPeriods : ImageButton
    lateinit var imgBtnBackToSchedulesList : ImageButton
    lateinit var rvAppointments : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_appointment,container,false)

        //Auxiliary procedures for storing and configuring view elements
        setRecicleViewParameters(view)
        sefImageButtonsParameters(view)

        presenter.setFragment(this)
        presenter.populateIndentifiedAppointmentList(listAppointmets)
        return view
    }

    override fun refreshAppointmentList(scheduleList: ArrayList<IndentifiedAppointment>) {
        rvAppointments.adapter = AppointmentListAdapter(scheduleList,presenter)
    }

    private fun sefImageButtonsParameters(view : View){
        imgBtnBackToDayPeriods = view.imgBtnBackToDayPeriods
        imgBtnBackToDayPeriods.setOnClickListener{
            //val act = context as ProfessionalDetailMVP.ProfessionalDetailViewImpl
            this.view.returnFromAppointmentFragment()
            //Reset temporary variables for identified appointments
            presenter.resetTemporaryVariablesFromIndentifiedAppointment()

        }

        imgBtnBackToSchedulesList = view.imgBtnBackToSchedulesList
        imgBtnBackToSchedulesList.setOnClickListener{
            val act = context as ProfessionalDetailMVP.ProfessionalDetailViewImpl
            act.initializeDayScheduleFragment()
            //Reset temporary variables for identified appointments
            presenter.resetTemporaryVariablesFromIndentifiedAppointment()
        }
    }

    private fun setRecicleViewParameters(view: View){
        rvAppointments = view.rvAppointments
        rvAppointments.layoutManager = LinearLayoutManager(context)
    }
}