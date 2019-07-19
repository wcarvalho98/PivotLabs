package com.ufrpe.br.pivotlabs.beans


/**
 *This class serves solelly to save an appointment and an appointment id separatedly
 * to be used when trying to identify an scheduled appoitment in the data base, thus
 * we only need to use the apointment id to fetch the appointment data
 */

class IndentifiedAppointment(var id:String,var appointment:Appointment )