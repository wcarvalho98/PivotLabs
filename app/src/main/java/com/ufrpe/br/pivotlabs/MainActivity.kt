package com.ufrpe.br.pivotlabs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.ufrpe.br.pivotlabs.R

class MainActivity : AppCompatActivity() {
    lateinit var imgBtnQuemSomos : ImageButton
    lateinit var imgBtnContato : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
