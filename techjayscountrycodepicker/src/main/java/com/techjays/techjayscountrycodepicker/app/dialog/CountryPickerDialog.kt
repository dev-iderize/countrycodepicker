package com.techjays.techjayscountrycodepicker.app.dialog


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techjays.techjayscountrycodepicker.R
import com.techjays.techjayscountrycodepicker.api.AppServices
import com.techjays.techjayscountrycodepicker.api.Response
import com.techjays.techjayscountrycodepicker.api.ResponseListener
import com.techjays.techjayscountrycodepicker.app.CountryCodeLibrary
import com.techjays.techjayscountrycodepicker.app.adapters.CountryPickerAdapter
import com.techjays.techjayscountrycodepicker.app.handler.CountryPickerDialogHandler
import com.techjays.techjayscountrycodepicker.app.models.CountryCode
import com.techjays.techjayscountrycodepicker.databinding.DialogCountrypickerBinding

class CountryPickerDialog : BottomSheetDialogFragment() {
    companion object {
        var TAG: String = CountryPickerDialog::class.java.simpleName
        var mBaseUrl: String = ""

        fun newInstance(
            urlString: String
        ): CountryPickerDialog {
            mBaseUrl = urlString
            return CountryPickerDialog()
        }
    }

    lateinit var mContentViewBinding: DialogCountrypickerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mContentViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_countrypicker, container, false)
        return mContentViewBinding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    override fun onPause() {
        super.onPause()
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        /*  val baseUrl = "http://34.224.39.147/api/portal/"*/
        val baseUrl = mBaseUrl
        CountryCodeLibrary.instance.baseUrl = baseUrl
        mContentViewBinding.handler = CountryPickerDialogHandler(this)
        mContentViewBinding.handler!!.getCountryCode()

    }


}