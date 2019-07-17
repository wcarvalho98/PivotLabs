package com.ufrpe.br.pivotlabs.professional_select.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.Doctor
import kotlinx.android.synthetic.main.professional_item.view.*

class ProfessionalAdapter(var items: ArrayList<Doctor>, var context: Context): RecyclerView.Adapter<ProfessionalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.professional_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvProfessionalName.text = items[position].name
        holder.tvSpeciality.text = items[position].speciality
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvProfessionalName = view.tvProfessionalName
        val tvDayPeriod = view.tvDayPeriod
        val tvSpeciality = view.tvSpeciality
    }

}