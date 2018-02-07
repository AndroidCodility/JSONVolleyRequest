package com.codility.jsonvolley.model

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

/**
 * Created by Govind on 2/6/2018.
 */
class AndroidObject(obj: JSONObject) : Serializable {
    var website: String? = null
    var topics: String? = null
    var facebook: String? = null
    var twitter: String? = null
    var youtube: String? = null
    var message: String? = null

    init {
        parseJsonObject(obj)
    }

    fun parseJsonObject(jsonObject: JSONObject) {
        try {
            website = jsonObject.getString("website")
            topics = jsonObject.getString("topics")
            facebook = jsonObject.getString("facebook")
            twitter = jsonObject.getString("twitter")
            youtube = jsonObject.getString("youtube")
            message = jsonObject.getString("message")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}