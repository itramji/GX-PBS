package com.ramji.intercom.status.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ramji.intercom.status.listener.OnTrunkItemClickListener
import com.ramji.intercom.status.R
import com.ramji.intercom.status.dummy.DummyContent


import com.ramji.intercom.status.dummy.DummyContent.DummyItem
import com.ramji.intercom.status.listener.OnIntercomClickLisetner
import kotlinx.android.synthetic.main.fragment_intercom_items.view.*

import kotlinx.android.synthetic.main.fragment_trunk_items.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnTrunkItemClickListener].
 */
class IntercomAdapter(
        private val mValues: List<DummyItem>)
    : RecyclerView.Adapter<IntercomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_intercom_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTitle.text = mValues[position].title
        holder.mValue.text = mValues[position].value
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.title
        val mValue: TextView = mView.value
    }
}
