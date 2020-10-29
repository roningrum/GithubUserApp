package com.roningrum.githubuserapp

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roningrum.githubuserapp.adapter.GithubAdapter
import com.roningrum.githubuserapp.data.Users

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: GithubAdapter
    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataRepository: IntArray
    private lateinit var dataFollower: IntArray
    private lateinit var dataFollowing: IntArray
    private lateinit var dataLocation: Array<String>
    private lateinit var dataAvatar: TypedArray

    private var githubUsers = arrayListOf<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvListUsers: RecyclerView = findViewById(R.id.rv_github_users)
        rvListUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = GithubAdapter(githubUsers)
        rvListUsers.adapter = adapter
        adapter.setOnItemClickCallback(object : GithubAdapter.OnItemClickCallback {
            override fun onItemClick(user: Users) {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERS, user)
                startActivity(intent)
            }

        })
//        adapter = GithubAdapter(this)
//        listView.adapter = adapter
        prepareData()
        addItemToList()
//        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->

//        }
    }

    private fun addItemToList() {
        for (position in dataName.indices) {
            val users = Users(
                dataName[position],
                dataUsername[position],
                dataLocation[position],
                dataRepository[position],
                dataCompany[position],
                dataFollower[position],
                dataFollowing[position],
                dataAvatar.getResourceId(position, -1)
            )
            githubUsers.add(users)
        }

    }

    private fun prepareData() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataLocation = resources.getStringArray(R.array.location)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
        dataRepository = resources.getIntArray(R.array.repository)
        dataFollower = resources.getIntArray(R.array.followers)
        dataFollowing = resources.getIntArray(R.array.following)
        dataRepository = resources.getIntArray(R.array.repository)
        dataCompany = resources.getStringArray(R.array.company)
    }
}