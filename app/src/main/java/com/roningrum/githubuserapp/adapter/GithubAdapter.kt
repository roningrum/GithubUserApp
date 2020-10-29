package com.roningrum.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.roningrum.githubuserapp.R
import com.roningrum.githubuserapp.remote.data.Items
import de.hdodenhof.circleimageview.CircleImageView


//listview
//class GithubAdapter internal constructor(private val context: Context) : BaseAdapter() {
//    internal var users = arrayListOf<Users>()
//
//    override fun getCount(): Int = users.size
//
//    override fun getItem(i: Int): Any = users[i]
//
//    override fun getItemId(i: Int): Long = i.toLong()
//
//    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
//        var itemView = view
//        if (itemView == null) {
//            itemView =
//                LayoutInflater.from(context).inflate(R.layout.item_github_user, viewGroup, false)
//        }
//        val viewHolder = ViewHolder(itemView as View)
//        val users = getItem(position) as Users
//        viewHolder.bind(users)
//        return itemView
//    }
//
//    class ViewHolder internal constructor(view: View) {

//
//        internal fun bind(user: Users) {
//            txtName.text = user.name
//            txtUserName.text = user.username
//            txtLocation.text = user.location
//            imgPhoto.setImageResource(user.avatar)
//        }
//    }
//}
class GithubAdapter(private val users: List<Items>) :
    RecyclerView.Adapter<GithubAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_github_user, parent, false)
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size


    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val txtName: TextView = view.findViewById(R.id.txt_user_name_profile)
        private val imgPhoto: CircleImageView = view.findViewById(R.id.img_user_photo)
        private val txtUserName: TextView = view.findViewById(R.id.txt_username)
        private val txtLocation: TextView = view.findViewById(R.id.txt_user_location)

        internal fun bind(user: Items) {
            txtName.text = user.login
//            imgPhoto.setImageResource(user.avatar_url)
        }
    }

//    interface OnItemClickCallback {
//        fun onItemClick(user: Users)
//    }
}




