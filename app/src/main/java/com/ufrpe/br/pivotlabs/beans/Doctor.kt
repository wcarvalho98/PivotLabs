package com.ufrpe.br.pivotlabs.beans

data class Doctor(var name: String = "", var speciality: String? = null, var schedule: HashMap<String, Boolean>?)