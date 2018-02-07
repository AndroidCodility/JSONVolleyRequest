package com.codility.jsonvolley

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.codility.jsonvolley.utils.Utility
import kotlinx.android.synthetic.main.string_request.*

/**
 * Created by Govind on 2/6/2018.
 */
class StringRequestActivity : AppCompatActivity() {
    //Change Your URL Here
    private val JSON_URL = Utility.BASE_URL.plus("string.json")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.string_request)

        //Set Title in Toolbar
        setTitle(R.string.string_request)
        // Show back Button in Toolbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        if (Utility.isOnline(this)) {
            sendRequest()
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
            tvResponse.text = getString(R.string.no_internet)
            progressBar.visibility = View.GONE
        }
    }

    private fun sendRequest() {
        Volley.newRequestQueue(this).add(StringRequest(JSON_URL,
                Response.Listener<String> { response ->
                    progressBar.visibility = View.GONE
                    showResponse(response)
                },
                Response.ErrorListener { error ->
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                }))
    }

    private fun showResponse(response: String) {
        if (tvResponse != null) {
            tvResponse.text = response
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}