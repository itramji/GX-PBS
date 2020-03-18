package com.ramji.intercom.status.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ramji.intercom.status.R
import com.ramji.intercom.status.SpacesItemDecoration
import com.ramji.intercom.status.adapter.BoardAdapter
import com.ramji.intercom.status.dummy.BoardData
import kotlinx.android.synthetic.main.fragment_board.*
import kotlin.random.Random

class BoardFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var boardDataList: ArrayList<BoardData>
    private lateinit var adapter:BoardAdapter

    private var lm: GridLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PreferenceManager.getDefaultSharedPreferences(requireContext()).registerOnSharedPreferenceChangeListener(this)
        recyclerView.addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.small_margin)))
        updateView()
    }

    private fun onItemClick(boardData: BoardData) {
        val name = boardData.name
        Toast.makeText(context, "$name is selected", Toast.LENGTH_SHORT).show()
    }

    private fun updateView() {
        boardDataList = ArrayList<BoardData>()

        val config = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("Config", "4/48")!!

        val count = (config.split("/")[1]).toInt()

        val spanCount = (config.split("/")[0]).toInt()

        for (x in 0 until spanCount) {
            val id = x+1
            boardDataList.add(BoardData("T$id",0, x))
        }

        for (x in spanCount until count+spanCount) {
            val id = (x-spanCount)+1
            boardDataList.add(BoardData("E$id",0, x))
        }


        if (lm == null) {
            lm = GridLayoutManager(activity, spanCount, GridLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = lm
        } else {
            lm?.spanCount = spanCount
        }

        adapter = BoardAdapter(if (spanCount == 4) 16f else 12f, boardDataList, ::onItemClick)
        recyclerView.adapter = adapter


    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, key: String?) {
        if (key == "Config") {
            updateView()
        }else if (key=="serverData"){
            val data = PreferenceManager.getDefaultSharedPreferences(context).getString("serverData", "%E0011$")

            val EorT = data[1].toString()
            val pos = data.subSequence(2,5).toString().toInt()
            if(adapter!=null){
                val boardData = boardDataList.single { it.name == EorT+pos }
                boardData.state =  data[5].toString().toInt()

                adapter.updateView(boardData, boardData.position)
            }
        }
    }

}
