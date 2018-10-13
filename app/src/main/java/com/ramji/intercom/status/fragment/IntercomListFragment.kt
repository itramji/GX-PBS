package com.ramji.intercom.status.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramji.intercom.status.GridDividerItemDecoration
import com.ramji.intercom.status.R
import com.ramji.intercom.status.adapter.IntercomAdapter
import com.ramji.intercom.status.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_trunk.view.*

/**
 * A fragment representing a list of Items.
 */
class IntercomListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_trunk, container, false)

        with(view.list) {
            //addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = IntercomAdapter(
                    arguments?.getParcelableArrayList(ARG_INERCOM_LIST)!!
            )
        }

        return view
    }

    companion object {

        const val ARG_INERCOM_LIST = "intercom-list"

        @JvmStatic
        fun newInstance(list: ArrayList<DummyContent.DummyItem>) =
                IntercomListFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_INERCOM_LIST, list)
                    }
                }
    }
}
