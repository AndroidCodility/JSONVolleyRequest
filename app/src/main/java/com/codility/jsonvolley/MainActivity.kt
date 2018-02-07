package com.codility.jsonvolley

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btStringRequest -> {
                startActivity(Intent(this, StringRequestActivity::class.java))
            }
            R.id.btJsonObjectRequest -> {
                startActivity(Intent(this, JsonObjectActivity::class.java))
            }
            R.id.btJsonArrayRequest -> {
                startActivity(Intent(this, JsonArrayActivity::class.java))
            }
            R.id.btImageRequest -> {
                startActivity(Intent(this, ImageVolleyActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btStringRequest.setOnClickListener(this)
        btJsonObjectRequest.setOnClickListener(this)
        btJsonArrayRequest.setOnClickListener(this)
        btImageRequest.setOnClickListener(this)
    }

    fun exitView(view: View?) {
        finish()
    }
}