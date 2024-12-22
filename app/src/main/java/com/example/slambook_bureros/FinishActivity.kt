package com.example.slambook_bureros

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.slambook_bureros.databinding.ActivityFinishBinding

class FinishActivity: AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }


    }
