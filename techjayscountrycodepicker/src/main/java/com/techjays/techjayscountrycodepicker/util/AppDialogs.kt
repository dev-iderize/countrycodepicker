package com.sj.covidradar.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.techjays.techjayscountrycodepicker.R
import java.util.*


object AppDialogs {

    private var progressDialog: Dialog? = null
    private var dialog: Dialog? = null
    private var custom_dialog: Dialog? = null
    private var selection_dialog: AlertDialog? = null
    @SuppressLint("StaticFieldLeak")
    private var bottom_dialog: BottomSheetDialog? = null
    private var mPopupWindow: PopupWindow? = null

    /**
     * Simple interface can be implemented for confirm an action via dialogs
     */
    interface ConfirmListener {
        fun yes()
    }

    interface OptionListener : ConfirmListener {
        fun no()
    }

    /**
     * Confirm actions that are critical before proceeding
     *
     * @param c
     * @param text
     * @param l
     */
    @SuppressLint("InlinedApi")
    fun optionalAction(
        c: Context,
        text: String,
        l: OptionListener?,
        yes: String? = "YES",
        no: String? = "NO"
    ) {
        val builder = AlertDialog.Builder(c, androidx.appcompat.R.style.AlertDialog_AppCompat)
        builder.setTitle(c.resources.getString(R.string.app_name))
        builder.setMessage(text)

        builder.setPositiveButton(yes) { dialog, _ ->
            l?.yes()
            dialog.dismiss()
        }

        builder.setNegativeButton(no) { dialog, _ ->
            l?.no()
            dialog.dismiss()
        }

        builder.setOnCancelListener { dialogInterface -> dialogInterface.dismiss() }
        builder.create().show()
    }


    /**
     * Confirm actions that are critical before proceeding
     *
     * @param c
     * @param text
     * @param l
     */
/*    @SuppressLint("InlinedApi")
    fun confirmAction(c: Context, title: String, text: String, l: ConfirmListener?) {
        try {
            val alertDialog: AlertDialog
            val builder =
                AlertDialog.Builder(c, androidx.appcompat.R.style.AlertDialog_AppCompat)
            builder.setTitle(title)
            builder.setMessage(text)

            builder.setPositiveButton(c.resources.getString(R.string.yes)) { dialog, _ ->
                l?.yes()
                dialog.dismiss()
            }
            builder.setNegativeButton(c.resources.getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
            builder.setOnCancelListener { dialogInterface -> dialogInterface.dismiss() }
            alertDialog = builder.create()
            alertDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

    /**
     * Confirm actions that are critical before proceeding
     *
     * @param c
     * @param text
     */
    @SuppressLint("InlinedApi")
    fun okAction(c: Context, text: String) {
        val alertDialog: AlertDialog
        val builder = AlertDialog.Builder(c, androidx.appcompat.R.style.AlertDialog_AppCompat)
        builder.setTitle(c.resources.getString(R.string.app_name))
        builder.setMessage(text)

        builder.setPositiveButton(c.resources.getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }

        builder.setOnCancelListener { dialogInterface -> dialogInterface.dismiss() }
        alertDialog = builder.create()
        alertDialog.show()

    }

/*
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun showProgressDialog(context: Context) {
        showProgressDialog(context, true)
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun showProgressDialog(context: Context, isCancelable: Boolean) {
        hideProgressDialog()
        progressDialog = Dialog(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_progress_custom, null)
        progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog!!.setContentView(view)
        progressDialog!!.setCancelable(isCancelable)
        progressDialog!!.show()
    }

    fun hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/


    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun showProgressDialog(context: Context) {
        showProgressDialog(context, true)
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun showProgressDialog(context: Context, isCancelable: Boolean) {
        hideProgressDialog()
        progressDialog = Dialog(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_progress_custom, null)
        progressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog!!.setContentView(view)
        progressDialog!!.setCancelable(isCancelable)
        progressDialog!!.show()
    }

    fun hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}