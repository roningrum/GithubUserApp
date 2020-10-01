package com.roningrum.githubuserapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.roningrum.githubuserapp.R
import com.roningrum.githubuserapp.data.Users
import de.hdodenhof.circleimageview.CircleImageView

class GithubAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var users = arrayListOf<Users>()

    override fun getCount(): Int = users.size

    override fun getItem(i: Int): Any = users[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if (itemView == null) {
            itemView =
                LayoutInflater.from(context).inflate(R.layout.item_github_user, viewGroup, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val users = getItem(position) as Users
        viewHolder.bind(users)
        return itemView
    }

    class ViewHolder internal constructor(view: View) {
        private val txtName: TextView = view.findViewById(R.id.txt_user_name_profile)
        private val imgPhoto: CircleImageView = view.findViewById(R.id.img_user_photo)
        private val txtUserName: TextView = view.findViewById(R.id.txt_username)
        private val txtLocation: TextView = view.findViewById(R.id.txt_user_location)

        internal fun bind(user: Users) {
            txtName.text = user.name
            txtUserName.text = user.username
            txtLocation.text = user.location
            imgPhoto.setImageResource(user.avatar)
        }
    }
}


