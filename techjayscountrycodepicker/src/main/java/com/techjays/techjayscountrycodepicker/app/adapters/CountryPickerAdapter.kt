package com.techjays.techjayscountrycodepicker.app.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.techjays.techjayscountrycodepicker.R
import com.techjays.techjayscountrycodepicker.app.dialog.CountryPickerDialog
import com.techjays.techjayscountrycodepicker.app.models.CountryCode
import com.techjays.techjayscountrycodepicker.databinding.InflateCountryItemBinding
import com.techjays.techjayscountrycodepicker.util.Utility
import okhttp3.internal.Util

class CountryPickerAdapter(
    private val mContext: CountryPickerDialog,
    private val mListData: ArrayList<CountryCode.CountryCodeData>
) : RecyclerView.Adapter<CountryPickerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(mContext.requireContext())
                .inflate(R.layout.inflate_country_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eachListData = mListData[position]
        (holder.mBinding as InflateCountryItemBinding).position = position
        holder.mBinding.data = eachListData
        holder.mBinding.name.text = eachListData.mName
        holder.mBinding.code.text = eachListData.mCountryCode
        val image =
            "https://force-field-dev.s3.amazonaws.com/country-flags/png_small/${eachListData.mShortcode.toLowerCase()}.png"
        //val smallImage=mContext.getString(R.string.)
        Utility.loadUserImage(
            image,
            holder.mBinding.loadimage
        )
        val intent = Intent()
        intent.action = "countryCodePicker"
        intent.putExtra("countryName", eachListData.mName)
        intent.putExtra("image", image)
        intent.putExtra("countryCode", eachListData.mCountryCode)
        intent.putExtra("countryShortCode", eachListData.mShortcode)
        intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        mContext.requireActivity().sendBroadcast(intent)

        holder.mBinding.executePendingBindings()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mListData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mBinding: ViewDataBinding? = DataBindingUtil.bind(itemView)
    }


}