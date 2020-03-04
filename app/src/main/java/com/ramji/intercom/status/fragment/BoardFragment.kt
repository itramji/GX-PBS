package com.ramji.intercom.status.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    }

    private fun updateView() {
        boardDataList = ArrayList<BoardData>()

        val config = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("Config", "4/48")!!

        val count = (config.split("/")[1]).toInt()

        val spanCount = (config.split("/")[0]).toInt()

        for (x in 1..count) {
            boardDataList.add(BoardData(Random.nextInt(0, 2)))
        }


        if (lm == null) {
            lm = GridLayoutManager(activity, spanCount, GridLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = lm
        } else {
            lm?.spanCount = spanCount
        }

        recyclerView.adapter = BoardAdapter(if (spanCount == 4) 16f else 12f, boardDataList, ::onItemClick)


    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, key: String?) {
        if (key == "Config") {
            updateView()
        }
    }

}
