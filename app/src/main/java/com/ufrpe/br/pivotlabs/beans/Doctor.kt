package com.ufrpe.br.pivotlabs.beans

data class Doctor(var name: String = "", var schedule: HashMap<String, Boolean>? = null, var speciality: String? = null)