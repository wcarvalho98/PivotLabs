package com.ufrpe.br.pivotlabs.professional_detail.view.day_period

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ufrpe.br.pivotlabs.R
import com.ufrpe.br.pivotlabs.beans.DayPeriod
import com.ufrpe.br.pivotlabs.beans.DaySchedule
import com.ufrpe.br.pivotlabs.professional_detail.ProfessionalDetailMVP
import kotlinx.android.synthetic.main.fragment_day_period.view.*

class DayPeriodFragment(var dayPeriodList: ArrayList<DayPeriod>,
                        val presenter: ProfessionalDetailMVP.ProfessionalDetailPresenterImpl,
                        val view : ProfessionalDetailMVP.ProfessionalDetailViewImpl):
                            Fragment(),ProfessionalDetailMVP.ProfessionalDetailViewImpl.DayPeriodFragmentImpl{

    companion object{
        var dayPeriodFragment : DayPeriodFragment? = null
    }
    private lateinit var rvDayPeriods: RecyclerView
    private lateinit var imgBtnBackToSchedulesList : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view  = inflater.inflate(R.layout.fragment_day_period,container,false)

        setRecyclerViewConfigurations(view)
        setImageButtonConfiguration(view)
        presenter.setFragment(this)
        presenter.populateDayPeriodList(dayPeriodList)
        return view
    }


    override fun refreshDayPeriodList(scheduleList: ArrayList<DayPeriod>) {
        rvDayPeriods.adapter = DayPeriodListAdapter(dayPeriodList,presenter,view)
    }


    private fun setRecyclerViewConfigurations(view: View){
        rvDayPeriods = view.rvDayPeriods
        rvDayPeriods.layoutManager = LinearLayoutManager(context)
    }

    private fun setImageButtonConfiguration(view : View){
        imgBtnBackToSchedulesList = view.imgBtnBackToSchedulesList
        imgBtnBackToSchedulesList.setOnClickListener{
            val act = context as ProfessionalDetailMVP.ProfessionalDetailViewImpl
            act.initializeDayScheduleFragment()
        }

    }

}