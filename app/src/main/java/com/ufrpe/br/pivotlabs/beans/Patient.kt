package com.ufrpe.br.pivotlabs.beans

data class Patient(var email: String = "", var city: String? = null, var image: String? = null,
                   var phone: HashMap<String, Boolean>? = null, var consult: HashMap<String, Boolean>? = null)