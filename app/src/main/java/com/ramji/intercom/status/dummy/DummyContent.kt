package com.ramji.intercom.status.dummy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, List<DummyChildItem>> = HashMap()

    private val COUNT = 3

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, createDummyChildItem())
    }

    private fun createDummyChildItem(): List<DummyChildItem> {
        var childList = ArrayList<DummyChildItem>()
        for (i in 1..10) {
            childList.add(DummyChildItem("Intercom " + i.toString(), "Airtel", "Updated 10 mins ago", i % 3 == 0))
        }
        return childList
    }

    private fun createDummyItem(position: Int): DummyItem {
        return DummyItem("Trunk " + position.toString(), "Airtel", "Updated 10 mins ago", position % 2 == 0)
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val content: String, val details: String, val isActive: Boolean) {
        override fun toString(): String = content
    }

    /**
     * A dummy item representing a piece of content.
     */
    @Parcelize
    data class DummyChildItem(val id: String, val content: String, val details: String, val isActive: Boolean) : Parcelable {
        override fun toString(): String = content
    }
}
