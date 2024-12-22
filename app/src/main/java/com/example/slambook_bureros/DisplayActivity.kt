package com.example.slambook_bureros

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.slambook_bureros.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        (intent.getSerializableExtra("UserInfo") as? UserInfo)?.let { populateFields(it) }

        binding.closeButton.setOnClickListener {
            navigateToCreateActivity()
        }
    }

    private fun populateFields(userInfo: UserInfo) {
        binding.apply {
            textFirstName.text = userInfo.firstName
            textLastName.text = userInfo.lastName
            textNickname.text = userInfo.nickname
            textHobby.text = userInfo.hobby
            textFavFood.text = userInfo.favFood
            textFavMovie.text = userInfo.favMovie
            textFavMusic.text = userInfo.favMusic
        }
    }

    private fun navigateToCreateActivity() {
        startActivity(Intent(this, CreateActivity::class.java))
        finish()
    }
}
