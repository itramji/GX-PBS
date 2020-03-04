package com.ramji.intercom.status.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramji.intercom.status.R
import com.ramji.intercom.status.dummy.BoardData
import kotlinx.android.synthetic.main.fragment_board_item.view.*

class BoardAdapter(private val textSize: Float, private var boardData: List<BoardData>, val onItemClick:(pos:BoardData)->Unit) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_board_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return boardData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onItemClick(boardData[position])}

        holder.itemView.ethernet_name.text = "E$position"
        holder.itemView.ethernet_name.setTextSize(TypedValue.COMPLEX_UNIT_SP ,textSize)
        holder.itemView.imageView3.setImageResource(if (boardData[position].state == 0) R.drawable.ic_router else R.drawable.ic_router_on)

    }

    fun updateViews(boardData: List<BoardData>){
        this.boardData = boardData
    }
}