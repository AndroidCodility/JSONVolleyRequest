package com.codility.jsonvolley.model

import org.json.JSONException
import org.json.JSONObject

class User(jsonObject: JSONObject) {
    var name: String? = null
    var title: String? = null
    var category: String? = null
    var url: String? = null

    init {
        parseJson(jsonObject)
    }

    private fun parseJson(jsonObject: JSONObject) {
        try {
            name = jsonObject.getString("name")
            title = jsonObject.getString("title")
            category = jsonObject.getString("category")
            url = jsonObject.getString("url")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}