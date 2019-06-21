package com.ufrpe.br.pivotlabs.beans

data class Doctor(var email: String = "", var speciality: String? = null, var city: String? = null,
                  var image: String? = null, var schedule: HashMap<String, Boolean>? = null)