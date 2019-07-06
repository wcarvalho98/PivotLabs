package com.ufrpe.br.pivotlabs.beans
/*Every doctor_schedule object has the id of the doctor and the
date  of the schedule:
    the list of schedules and whether or not the schedule is free or not
* */
data class Doctors_Schedule(var idDoctor: String,
                            var date: HashMap<String,HashMap<String,ArrayList<HashMap<String,Boolean>>>>)