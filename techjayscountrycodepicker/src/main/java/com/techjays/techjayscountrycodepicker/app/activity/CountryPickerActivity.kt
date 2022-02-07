package com.techjays.techjayscountrycodepicker.app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.techjays.techjayscountrycodepicker.R

class CountryPickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_picker)
        val baseUrl = intent.getStringExtra("base_url")
        Toast.makeText(applicationContext, baseUrl, Toast.LENGTH_LONG).show()
    }
}