package com.codility.jsonvolley

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.codility.jsonvolley.adapter.MyAdapter
import com.codility.jsonvolley.model.Users
import com.codility.jsonvolley.utils.Utility
import kotlinx.android.synthetic.main.json_array.*

/**
 * Created by Govind on 2/6/2018.
 */
class JsonArrayActivity : AppCompatActivity() {
    //Change Your URL Here
    private val JSON_URL = Utility.BASE_URL.plus("json_array.json")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.json_array)

        //Set Title in Toolbar
        setTitle(R.string.json_array_request)
        // Show back Button in Toolbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        tvHeader.text = getString(R.string.json_recyclerview_sample)
        if (Utility.isOnline(this)) {
            progressBar.visibility = View.VISIBLE
            sendArrayRequest()
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendArrayRequest() {
        val jsonArrayReq = JsonArrayRequest(JSON_URL,
                Response.Listener { response ->
                    progressBar.visibility = View.GONE
                    showResponse(Users(response))
                },
                Response.ErrorListener { error ->
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                })

        // Increase Timeout to 15 secs.
        jsonArrayReq.retryPolicy = DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        // Adding JsonObject request to request queue
        Volley.newRequestQueue(this).add(jsonArrayReq)
    }

    private fun showResponse(posts: Users) {
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(this)
            val myAdapter = MyAdapter(posts)
            recyclerView.adapter = myAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}