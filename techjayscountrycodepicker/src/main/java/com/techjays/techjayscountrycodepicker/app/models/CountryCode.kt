package com.techjays.techjayscountrycodepicker.app.models

import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName
import com.techjays.techjayscountrycodepicker.api.Response

class CountryCode : Response() {

    @SerializedName("data")
    var mData = ArrayList<CountryCodeData>()

    class CountryCodeData : BaseObservable() {

        @SerializedName("short_code")
        var mShortcode = ""

        @SerializedName("name")
        var mName = ""

        @SerializedName("code")
        var mCountryCode = ""
    }

}