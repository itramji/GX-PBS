package com.ramji.intercom.status.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ramji.intercom.status.listener.OnTrunkItemClickListener
import com.ramji.intercom.status.R
import com.ramji.intercom.status.dummy.DummyContent


import com.ramji.intercom.status.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_trunk_items.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnTrunkItemClickListener].
 */
class TruckAdapter(
        private val mValues: List<DummyItem>,
        private val mListener: OnTrunkItemClickListener?)
    : RecyclerView.Adapter<TruckAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            mListener?.onTrunkSelect(DummyContent.ITEM_MAP[item.id] as ArrayList<DummyContent.DummyChildItem>)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_trunk_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mNameView.text = item.content
        holder.mDetailView.text = item.details
        holder.mBgLayout.background = if(item.isActive) holder.itemView.context.getDrawable(R.drawable.trunk_active_bg) else null

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.trunk_id
        val mNameView: TextView = mView.provider_name
        val mDetailView: TextView = mView.detail_view
        val mBgLayout: ImageView = mView.imageView
    }
}
