package com.ufrpe.br.pivotlabs.professional_select.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.Doctor
import com.ufrpe.br.pivotlabs.professional_detail.view.ProfessionalDetailActivity
import kotlinx.android.synthetic.main.professional_item.view.*

class ProfessionalAdapter(var itemKeys: ArrayList<String>,var items: ArrayList<Doctor>, var context: Context) :
    RecyclerView.Adapter<ProfessionalAdapter.ViewHolder>() {

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
        holder.item.setOnClickListener {
            var intent = Intent(context, ProfessionalDetailActivity::class.java)
            intent.putExtra("professional_id",itemKeys[position])
            intent.putExtra("professional_name",items[position].name)
            intent.putExtra("professional_speciality",items[position].speciality)
            context.startActivity(intent)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item = view.cl_prof_item
        val tvProfessionalName = view.tvProfessionalName
        val tvDayPeriod = view.tvDayPeriod
        val tvSpeciality = view.tvSpeciality
    }

}