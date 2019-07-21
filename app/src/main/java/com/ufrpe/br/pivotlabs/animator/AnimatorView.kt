package com.ufrpe.br.pivotlabs.animator

import android.view.View
import android.view.animation.AnimationUtils

class AnimatorView {

    companion object {
        fun fade(view: View, fadeIn: Boolean) {
            val fade = if (fadeIn) android.R.anim.fade_in else android.R.anim.fade_out
            val animation = AnimationUtils.loadAnimation(view.context, fade)
            view.animation = animation
            view.animate().duration = android.R.integer.config_mediumAnimTime.toLong()
        }

        fun invisible(view: View, visible: Boolean) {
            view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        }
    }

}