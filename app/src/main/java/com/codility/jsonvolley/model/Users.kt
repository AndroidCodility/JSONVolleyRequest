package com.codility.jsonvolley.model

import org.json.JSONArray
import org.json.JSONException
import java.util.*

class Users(jsonArray: JSONArray) {
    private var users: MutableList<User>? = null

    init {
        parseJson(jsonArray)
    }

    private fun parseJson(jsonArray: JSONArray) {
        users = ArrayList<User>()
        for (i in 0 until jsonArray.length()) {
            try {
                val jsonObject = jsonArray.getJSONObject(i)
                users!!.add(User(jsonObject))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    fun getPosts(): List<User>? {
        return users
    }
}