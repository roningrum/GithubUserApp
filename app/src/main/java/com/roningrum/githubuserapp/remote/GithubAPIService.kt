package com.roningrum.githubuserapp.remote

import com.roningrum.githubuserapp.remote.data.Users
import com.roningrum.githubuserapp.remote.response.DetailResponse
import com.roningrum.githubuserapp.remote.response.SearchResponse
import com.roningrum.githubuserapp.util.Util.github_token_access
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPIService {
    //test users
    @GET("users")
    @Headers("Authorization: token $github_token_access")
    fun showUsers(): Call<List<Users>>

    @GET("search/users")
    @Headers("Authorization: token $github_token_access")
    fun searchUsers(@Query("q") keyword: String): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token $github_token_access")
    fun getUserDetail(@Path("username") username: String): Call<DetailResponse>
}