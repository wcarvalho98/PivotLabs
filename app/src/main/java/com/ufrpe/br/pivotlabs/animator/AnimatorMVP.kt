package com.ufrpe.br.pivotlabs.animator

import android.view.View

interface AnimatorMVP {

    fun fade(view: View, fadeIn: Boolean)
    fun invisible(view: View, visible: Boolean)
}