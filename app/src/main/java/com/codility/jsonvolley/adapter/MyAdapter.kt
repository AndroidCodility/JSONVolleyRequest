package com.codility.jsonvolley.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.codility.jsonvolley.R
import com.codility.jsonvolley.model.User
import com.codility.jsonvolley.model.Users

/**
 * Created by Govind on 2/6/2018.
 */
class MyAdapter(private val userList: Users) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList.getPosts()!!.get(position))
    }

    override fun getItemCount(): Int {
        return userList.getPosts()!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_user, parent, false))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: User) {
            val tvName = itemView.findViewById<TextView>(R.id.name)
            val title = itemView.findViewById<TextView>(R.id.title)
            val category = itemView.findViewById<TextView>(R.id.category)
            val url = itemView.findViewById<TextView>(R.id.url)
            tvName.text = user.name;
            title.text = user.title
            category.text = user.category
            url.text = user.url
        }
    }
}