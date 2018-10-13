package com.ramji.intercom.status.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramji.intercom.status.listener.OnTrunkItemClickListener
import com.ramji.intercom.status.R
import com.ramji.intercom.status.adapter.TruckAdapter
import com.ramji.intercom.status.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_intercom_items.*
import kotlinx.android.synthetic.main.fragment_trunk.view.*

/**
 * A fragment representing a list of Items.
 */
class TrunkListFragment : Fragment(), OnTrunkItemClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_trunk, container, false)

        with(view.list) {
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(context)
            adapter = TruckAdapter(
                    DummyContent.ITEMS,
                    this@TrunkListFragment
            )
        }

        return view
    }

    override fun onTrunkSelect(title: String,list: ArrayList<DummyContent.DummyItem>) {
        childFragmentManager.beginTransaction().replace(R.id.child_container, IntercomListFragment.newInstance(list)).addToBackStack(IntercomListFragment.javaClass.simpleName).commit()
        requireActivity().title = title
    }

}
