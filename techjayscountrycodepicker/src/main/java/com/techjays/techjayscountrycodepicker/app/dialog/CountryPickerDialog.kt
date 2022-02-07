package com.techjays.techjayscountrycodepicker.app.dialog


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.techjays.techjayscountrycodepicker.R
import com.techjays.techjayscountrycodepicker.databinding.DialogCountrypickerBinding

class CountryPickerDialog:BottomSheetDialogFragment() {
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
        return R.style.BottomSheetDialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init(){
    }
}