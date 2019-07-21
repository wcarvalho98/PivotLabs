package com.ufrpe.br.pivotlabs.animator

import android.view.View
import android.view.animation.AnimationUtils

open class AnimatorView: AnimatorMVP {

    override fun fade(view: View, fadeIn: Boolean) {
        val fade = if (fadeIn) android.R.anim.fade_in else android.R.anim.fade_out
        val animation = AnimationUtils.loadAnimation(view.context, fade)
        view.animation = animation
        view.animate().setDuration(android.R.integer.config_mediumAnimTime.toLong()).start()
    }

    override fun invisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

}