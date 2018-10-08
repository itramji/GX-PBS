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
        private val mValues: List<DummyContent.DummyChildItem>,
        private val mListener: OnIntercomClickLisetner?)
    : RecyclerView.Adapter<IntercomAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyContent.DummyChildItem
            mListener?.onIntercomSelect(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_intercom_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mBgLayout.background = if(item.isActive) holder.itemView.context.getDrawable(R.drawable.intercom_active_bg) else null

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.intercom_id
        val mBgLayout: ImageView = mView.imageView2
    }
}
