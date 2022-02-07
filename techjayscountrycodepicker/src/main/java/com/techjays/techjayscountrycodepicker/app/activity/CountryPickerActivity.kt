package com.techjays.techjayscountrycodepicker.app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.techjays.techjayscountrycodepicker.R
import com.techjays.techjayscountrycodepicker.app.CountryCodeLibrary
import com.techjays.techjayscountrycodepicker.app.dialog.CountryPickerDialog

class CountryPickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_picker)
        initView()
    }

    private fun initView() {
        val baseUrl = intent.getStringExtra("base_url")
        CountryCodeLibrary.instance.baseUrl = baseUrl!!
        openCountryDialog(baseUrl)
    }
    private fun openCountryDialog(url:String) {

        val dialogFragment = CountryPickerDialog.newInstance( url)
        dialogFragment.show(
            this.supportFragmentManager,
            "Country Picker dialog"
        )

    }
}