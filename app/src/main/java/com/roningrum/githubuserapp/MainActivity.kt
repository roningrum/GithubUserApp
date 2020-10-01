package com.roningrum.githubuserapp

import android.content.res.TypedArray
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.roningrum.githubuserapp.adapter.GithubAdapter
import com.roningrum.githubuserapp.data.Users

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: GithubAdapter
    private lateinit var dataUsername: Array<String>
    private lateinit var dataName: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataAvatar: TypedArray

    private var githubUsers = arrayListOf<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView: ListView = findViewById(R.id.lv_github_users)
        adapter = GithubAdapter(this)
        listView.adapter = adapter

        prepareData()
        addItemToList()
    }

    private fun addItemToList() {
        for (position in dataName.indices) {
            val users = Users(
                dataName[position],
                dataUsername[position],
                dataLocation[position],
                dataAvatar.getResourceId(position, -1)
            )
            githubUsers.add(users)
        }
        adapter.users = githubUsers
    }

    private fun prepareData() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataLocation = resources.getStringArray(R.array.location)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
    }
}