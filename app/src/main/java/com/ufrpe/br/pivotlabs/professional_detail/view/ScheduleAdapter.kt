package com.ufrpe.br.pivotlabs.professional_detail.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import kotlinx.android.synthetic.main.popup_window.view.*
import kotlinx.android.synthetic.main.schedule_item.view.*

class ScheduleAdapter(val items: ArrayList<DaySchedule>,val context:Context):
                                                                RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {


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
            var pop_win = PopupWindow(context)
            var view = LayoutInflater.from(context).inflate(R.layout.popup_window,null)
            pop_win.contentView = view
            view.imgBtnClosePopUp.setOnClickListener{
                pop_win.dismiss()
            }
            pop_win.showAsDropDown(holder.item)
        }
    }


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val item  = view.cvScheduleDate
        val tvDate = view.tvDate
    }

}