package com.ramji.intercom.status.listener

import com.ramji.intercom.status.dummy.DummyContent

interface OnIntercomClickLisetner {
    fun onIntercomSelect(item: DummyContent.DummyItem)
}