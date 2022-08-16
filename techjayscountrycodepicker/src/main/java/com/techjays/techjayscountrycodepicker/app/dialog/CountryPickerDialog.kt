package com.techjays.techjayscountrycodepicker.app.dialog


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techjays.techjayscountrycodepicker.R
import com.techjays.techjayscountrycodepicker.app.CountryCodeLibrary
import com.techjays.techjayscountrycodepicker.app.handler.CountryPickerDialogHandler
import com.techjays.techjayscountrycodepicker.databinding.DialogCountrypickerBinding

@Suppress("DEPRECATION")
class CountryPickerDialog : BottomSheetDialogFragment() {
    companion object {
        var TAG: String = CountryPickerDialog::class.java.simpleName
        var mBaseUrl: String = ""
        private lateinit var mContext: Context

        fun newInstance(
            urlString: String,
            aContext: Context
        ): CountryPickerDialog {
            mBaseUrl = urlString
            mContext = aContext
            return CountryPickerDialog()
        }
    }

    lateinit var mContentViewBinding: DialogCountrypickerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        dialog?.setOnShowListener {
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
        }
        mContentViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_countrypicker, container, false)
        return mContentViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        /*  val baseUrl = "http://34.224.39.147/api/portal/"*/
        val baseUrl = mBaseUrl
        CountryCodeLibrary.instance.baseUrl = baseUrl
        mContentViewBinding.handler = CountryPickerDialogHandler(this)
        mContentViewBinding.handler!!.getCountryCode()

    }

    override fun getTheme(): Int {
        return R.style.DialogStyleCountryPicker
    }

    override fun onPause() {
        super.onPause()
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }


}