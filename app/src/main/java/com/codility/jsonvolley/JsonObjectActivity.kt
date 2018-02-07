package com.codility.jsonvolley

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.codility.jsonvolley.model.AndroidObject
import com.codility.jsonvolley.utils.Utility
import kotlinx.android.synthetic.main.string_request.*
import org.json.JSONObject
import java.util.*

/**
 * Created by Govind on 2/6/2018.
 */
class JsonObjectActivity : AppCompatActivity() {
    private var JSON_URL = "http://192.168.1.31/json/json_object.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.string_request)

        //Set Title in Toolbar
        setTitle(R.string.json_object_request)
        // Show back Button in Toolbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        /*
        **Click Event for Get Request
        */
        if (Utility.isOnline(this)) {
            sendGetRequest()
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
            tvResponse.text = getString(R.string.no_internet)
            progressBar.visibility = View.GONE
        }

        /*
        **Click Event for POST Request
        */
        btPostRequest.visibility = View.VISIBLE
        btPostRequest.setOnClickListener(View.OnClickListener {
            if (Utility.isOnline(this)) {
                progressBar.visibility = View.VISIBLE
                sendPostRequest()
            } else {
                Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
                tvResponse.text = getString(R.string.no_internet)
                progressBar.visibility = View.GONE
            }
        })

        /*
        **Click Event for User Header Request
        */
        btPostWithHeader.visibility = View.VISIBLE
        btPostWithHeader.setOnClickListener(View.OnClickListener {
            if (Utility.isOnline(this)) {
                progressBar.visibility = View.VISIBLE
                sendPostHeaderRequest()
            } else {
                Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
                tvResponse.text = getString(R.string.no_internet)
                progressBar.visibility = View.GONE
            }
        })

    }

    private fun sendGetRequest() {
        val jsonObjectReq = JsonObjectRequest(JSON_URL, null,
                Response.Listener<JSONObject> { response ->
                    progressBar.visibility = View.GONE
                    showResponse(response, "Showing GET Request Response...")
                },
                Response.ErrorListener {
                    progressBar.visibility = View.GONE
                })

        jsonObjectReq.retryPolicy = DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        // Adding JsonObject request to request queue
        Volley.newRequestQueue(this).add(jsonObjectReq)
    }

    private fun sendPostRequest() {
        val jsonObjReq = object : JsonObjectRequest(Request.Method.POST, JSON_URL,
                Response.Listener { response ->
                    progressBar.visibility = View.GONE
                    showResponse(response, "Showing POST Request Response...")
                },
                Response.ErrorListener { error ->
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }) {

            // You can send parameters as well with POST request....
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["name"] = "Govind"
                params["email"] = "xyz@gmail.com"
                params["password"] = "1234567"
                return params
            }
        }

        // Increase Timeout to 15 secs.
        jsonObjReq.retryPolicy = DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        Volley.newRequestQueue(this).add(jsonObjReq)
    }

    private fun sendPostHeaderRequest() {
        val jsonObjReq = object : JsonObjectRequest(Request.Method.POST, JSON_URL,
                Response.Listener { response ->
                    progressBar.visibility = View.GONE
                    showResponse(response, "Showing POST Header Request Response...")
                },
                Response.ErrorListener { error ->
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }) {

            // You can send parameters as header with POST request....
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["Authorisation"] = "xxxxxxxxxxxxxxx"
                return headers
            }
        }
        jsonObjReq.retryPolicy = DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        Volley.newRequestQueue(this).add(jsonObjReq)
    }

    private fun showResponse(response: JSONObject, header_info: String) {
        val androidObject = AndroidObject(response)
        tvHeader.text = header_info
        Log.e("Response ", response.toString())
        val text = (" Website: " + androidObject.website
                + "\n\n Topics: " + androidObject.topics
                + "\n\n Facebook: " + androidObject.facebook
                + "\n\n Twitter: " + androidObject.twitter
                + "\n\n Youtube: " + androidObject.youtube
                + "\n\n Message: " + androidObject.message)

        if (tvResponse != null) {
            tvResponse.text = text
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}