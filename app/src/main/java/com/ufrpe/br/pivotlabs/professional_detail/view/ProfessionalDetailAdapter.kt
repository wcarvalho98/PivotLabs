package com.ufrpe.br.pivotlabs.professional_detail.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.ScheduleActivity
import com.ufrpe.br.pivotlabs.beans.Schedule
import kotlinx.android.synthetic.main.professional_schedule_item.view.*

class ProfessionalDetailAdapter(var items: ArrayList<Schedule>, var context: Context) :
    RecyclerView.Adapter<ProfessionalDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.professional_schedule_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.startTime.text = items[position].start
        holder.finish.text = items[position].finish
        holder.day.text = items[position].day

        holder.btn.setOnClickListener {
            (context as ScheduleActivity).mark(position + 1)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var btn = view.btnSelectHour
        var startTime = view.tvStartTime
        var finish = view.tvFinishTime
        var day = view.tvDay
    }
}