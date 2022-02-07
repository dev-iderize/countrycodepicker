package com.techjays.techjayscountrycodepicker.app.adapters

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

class CountryPickerAdapter(
    private val mContext: CountryPickerDialog,
    private val mListData: ArrayList<CountryCode>
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