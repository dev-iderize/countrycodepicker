package com.techjays.techjayscountrycodepicker.api


import androidx.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

import java.io.Serializable

open class Response : Serializable, BaseObservable() {

    @SerializedName("result")
    var responseStatus: Boolean? = false

    @SerializedName("msg")
    var responseMessage = ""

    @SerializedName("next_link")
    var next_link: Boolean = false

    var requestType: Int? = null
}


