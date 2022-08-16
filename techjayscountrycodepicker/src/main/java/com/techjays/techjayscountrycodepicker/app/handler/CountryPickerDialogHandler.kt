package com.techjays.techjayscountrycodepicker.app.handler

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.techjays.techjayscountrycodepicker.api.AppServices
import com.techjays.techjayscountrycodepicker.api.Response
import com.techjays.techjayscountrycodepicker.api.ResponseListener
import com.techjays.techjayscountrycodepicker.app.adapters.CountryPickerAdapter
import com.techjays.techjayscountrycodepicker.app.dialog.CountryPickerDialog
import com.techjays.techjayscountrycodepicker.app.models.CountryCode

class CountryPickerDialogHandler(private val mContext: CountryPickerDialog) : ResponseListener {

    fun getCountryCode() {
        AppServices.getCountryData(mContext.requireContext(), this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResponse(r: Response?) {

        if (r != null) {
            if (r.requestType == AppServices.API.getcountrycode.hashCode()) {
                if (r.responseStatus!!) {
                    val data = (r as CountryCode)
                    if (mContext.isAdded && mContext != null) {
                        val layoutManager = LinearLayoutManager(mContext.requireContext())
                        mContext.mContentViewBinding.countryRecyclerview.layoutManager =
                            LinearLayoutManager(mContext.requireContext())
                        mContext.mContentViewBinding.countryData = data
                        mContext.mContentViewBinding.countryRecyclerview.adapter =
                            CountryPickerAdapter(
                                mContext, data.mData
                            )
                        mContext.mContentViewBinding.countryRecyclerview.adapter?.notifyDataSetChanged()
                    } else
                        return
                } else {
                    Toast.makeText(mContext.requireContext(), r.responseMessage, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

    }
}