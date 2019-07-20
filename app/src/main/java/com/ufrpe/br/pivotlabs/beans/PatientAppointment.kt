package com.ufrpe.br.pivotlabs.beans

data class PatientAppointment(var doctorId : String = "",
                              var apointmentId : String="",
                              var dayPeriod : String="",
                              var date : String = "")