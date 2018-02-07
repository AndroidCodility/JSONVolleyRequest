package com.codility.jsonvolley

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.codility.jsonvolley.utils.Utility
import kotlinx.android.synthetic.main.image_view.*


@Suppress("DEPRECATION")
/**
 * Created by Govind on 2/6/2018.
 */
class ImageVolleyActivity : AppCompatActivity() {
    private val imageUrl = "https://raw.githubusercontent.com/AndroidCodility/Picasso-RecyclerView/master/images/marshmallow.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_view)

        //Set Title in Toolbar
        setTitle(R.string.image_request)
        // Show back Button in Toolbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        if (Utility.isOnline(this)) {
            progressBar.visibility = View.VISIBLE
            sendImageRequest()
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendImageRequest() {
        Volley.newRequestQueue(this).add(ImageRequest(imageUrl, Response.Listener<Bitmap> { response ->
            progressBar.visibility = View.GONE
            imageView.setImageBitmap(response)
        }, 0, 0, null, Response.ErrorListener { error ->
            progressBar.visibility = View.GONE
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        }))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}