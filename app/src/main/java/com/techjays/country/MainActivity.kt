package com.techjays.country

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.techjays.techjayscountrycodepicker.app.activity.CountryPickerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBaseURL()
    }

    private fun initBaseURL() {
        val intent = Intent(this, CountryPickerActivity::class.java)
        intent.putExtra("base_url","https://dev.joinforcefield.com/api/portal/")
        startActivity(intent)
    }
}