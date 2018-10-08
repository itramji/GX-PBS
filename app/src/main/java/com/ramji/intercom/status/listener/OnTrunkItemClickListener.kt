package com.ramji.intercom.status.listener

import com.ramji.intercom.status.dummy.DummyContent

interface OnTrunkItemClickListener {
    fun onTrunkSelect(list: ArrayList<DummyContent.DummyChildItem>)
}