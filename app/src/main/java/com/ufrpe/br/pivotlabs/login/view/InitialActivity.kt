package com.ufrpe.br.pivotlabs.login.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_initial.*
import android.os.Handler
import com.ufrpe.br.pivotlabs.R

class InitialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        setAnimation(ivPivot)
        setAnimation(tvPivot)
        ivPivot.animate().alpha(1.0F).setDuration(500).start()
        tvPivot.animate().alpha(1.0F).setDuration(500).start()

        val handler = Handler()
        handler.postDelayed({
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, ivPivot, ViewCompat.getTransitionName(ivPivot)!!)
            val i = Intent(this, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i, options.toBundle())
            handler.postDelayed({this.finish()}, 1000)
        }, 3000)
    }

    private fun setAnimation(viewToAnimate: View) {
        val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.fade_in)
        viewToAnimate.animation = animation
    }
}
