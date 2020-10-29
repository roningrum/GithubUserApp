package com.roningrum.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roningrum.githubuserapp.remote.GithubAPIConfig
import com.roningrum.githubuserapp.remote.GithubAPIService
import com.roningrum.githubuserapp.remote.data.Items
import com.roningrum.githubuserapp.remote.data.Users
import com.roningrum.githubuserapp.remote.response.DetailResponse
import com.roningrum.githubuserapp.remote.response.SearchResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    val listUsers = MutableLiveData<List<Users>>()
    val listItems = MutableLiveData<List<Items>>()
    val listFailed = MutableLiveData<Boolean>()
    var login: String = ""

    fun getUsers() = GlobalScope.launch {
        delay(5000)
        val client = GithubAPIConfig.retrofitClient?.create(GithubAPIService::class.java)
        client?.showUsers()?.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                when {
                    response.code() == 200 -> {
                        listUsers.value = response.body()
                    }
                    response.code() == 401 -> {
                        listFailed.postValue(true)
                    }
                    else -> {
                        listFailed.postValue(true)
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e("Pesan", "Pesan ${t.message}")
            }

        }
        )
    }

    fun getSearchUserResult(query: String) = GlobalScope.apply {
        val client = GithubAPIConfig.retrofitClient?.create(GithubAPIService::class.java)
        client?.searchUsers(query)?.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                when {
                    response.code() == 200 -> {
                        listItems.value = response.body()?.items
                    }
                    response.code() == 400 -> {
                        listFailed.value = true
                    }
                    response.code() == 401 -> {
                        listFailed.value = true
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("Pesan Error", "${t.message}")
            }

        })
    }

    fun getDetailUser(username: String) {
        val client = GithubAPIConfig.retrofitClient?.create(GithubAPIService::class.java)
        client?.getUserDetail(username)?.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                when {
                    response.code() == 200 -> {
                        response.body().let { it ->
                            if (it != null) {
                                Log.d("Yang Login", "Login ${it.name}")
                            }
                        }
                    }
                    response.code() == 400 -> {
                        listFailed.value = true
                    }
                    response.code() == 401 -> {
                        listFailed.value = true
                    }
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e("Pesan Error", "${t.message}")
            }

        })
    }
}