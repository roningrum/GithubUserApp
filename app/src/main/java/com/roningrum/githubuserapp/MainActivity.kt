package com.roningrum.githubuserapp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roningrum.githubuserapp.adapter.GithubAdapter
import com.roningrum.githubuserapp.remote.data.Items
import com.roningrum.githubuserapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.layout_search_activity.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: GithubAdapter
    private var githubUsers: ArrayList<Items> = arrayListOf()
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            UserViewModel::class.java
        )

        val rvListUsers: RecyclerView = findViewById(R.id.rv_github_users)
        rvListUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        search_users_github.onActionViewExpanded()
        Handler().postDelayed(Runnable { search_users_github.clearFocus() }, 300)
        search_users_github.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                userViewModel.getSearchUserResult(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        userViewModel.listItems.observe(this, {
            githubUsers.clear()
            githubUsers.addAll(it)
            adapter = GithubAdapter(githubUsers)
            rvListUsers.adapter = adapter
            Log.d("Items", "Hasil Pencarian kamu adalah $githubUsers")
        })
        userViewModel.listFailed.observe(this, {
            Toast.makeText(applicationContext, "Oops There is something wrong", Toast.LENGTH_SHORT)
                .show()
        })

    }
}