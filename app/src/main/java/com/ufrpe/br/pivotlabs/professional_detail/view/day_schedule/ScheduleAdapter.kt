package com.ufrpe.br.pivotlabs.professional_detail.view.day_schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import com.ufrpe.br.pivotlabs.professional_detail.presenter.ProfessionalDetailPresenter
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity
import kotlinx.android.synthetic.main.schedule_item.view.*

class ScheduleAdapter(val items: ArrayList<DaySchedule>,
                      val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl,
                      val view : ProfessionalDetailMVP.ProfessionalDetailViewImpl):
                                                                RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    /**
     * Definind adapter that displays the date, but
     * the items selected are an arrayList of DaySchedule objects.
     * the DaySchedule Object selected has a list of objects called DayPeriod
     * The DayPeriods of the DaySchedule selected are then
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvDate.text = items[position].daySchedule_id
        holder.item.setOnClickListener{
            //Setting date of the PatientAppointment to be saved in the backend
            presenter.onDateChosen(items[position].daySchedule_id)
            //Here an also in the other switch methord the instance ViewImpl acts as a listener
            // for sending data to other fragments
            view.switchToDayPeriodFragment(items[position].listPeriods)
        }
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val item  = view.cvScheduleDate
        val tvDate = view.tvDate
    }

}