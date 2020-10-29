package com.roningrum.githubuserapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.roningrum.githubuserapp.data.Users
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USERS = "EXTRA_PERSON"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val userGithub: Users = intent.getParcelableExtra(EXTRA_USERS)!!
        img_user_photo_detail.setImageResource(userGithub.avatar)
        tv_nama_detail.text = userGithub.name
        tv_follower_detail.text = userGithub.followers.toString()
    }
}