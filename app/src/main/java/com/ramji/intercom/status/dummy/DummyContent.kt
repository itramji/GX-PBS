package com.ramji.intercom.status.dummy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<String> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, List<DummyItem>> = HashMap()



    init {

            addItem()

    }

    private fun addItem() {
        ITEMS.add("GX206")
        ITEM_MAP.put("GX206", getGx206List())

        ITEMS.add("GX308")
        ITEM_MAP.put("GX308", getGx308List())
    }

    private fun getGx206List():List<DummyItem>{
        var map:MutableList<DummyItem> =   ArrayList();
        map.add(DummyItem("Default extension number","30 - 35"))
        map.add(DummyItem("Call forward any time","717 + ext"))
        map.add(DummyItem("Call forward when busy","718 + ext"))
        map.add(DummyItem("Call follow me","719 + ext"))
        map.add(DummyItem("Do not disturb","716 + ext"))
        map.add(DummyItem("Swap call","Flash + 6 ( In Speech )"))

        map.add(DummyItem("Feature cancellation","7900"))
        map.add(DummyItem("Hot line with extension","751 + 0/1 + ext"))
        map.add(DummyItem("Hot line with trunk","752 + 0/1 + trk"))
        map.add(DummyItem("Cancel hot line","7910"))
        map.add(DummyItem("Call pickup","*"))
        map.add(DummyItem("Call pickup other than trunk incoming","74"))

        map.add(DummyItem("Barge in","766 + ext"))
        map.add(DummyItem("Intrude","768 + ext"))
        map.add(DummyItem("Call privacy","715 + ext + 0/1"))
        map.add(DummyItem("Paging announcement","767"))
        map.add(DummyItem("Emergency extension number","70"))
        map.add(DummyItem("Trunk redial","611"))
        map.add(DummyItem("Direct fetch trunk","8+(1-2)"))

        map.add(DummyItem("Abbreviated dialing","62 + Code Number[00-99]"))
        map.add(DummyItem("Attending camped call","Flash + *85"))
        map.add(DummyItem("Conference","Flash + 7 ( In Speech )"))
        map.add(DummyItem("Auto call back ","72"))

        return map
    }

    private fun getGx308List():List<DummyItem>{


        var map:MutableList<DummyItem> =   ArrayList();
        map.add(DummyItem("Default extension number","30 - 37"))
        map.add(DummyItem("Call forward any time","717 + ext"))
        map.add(DummyItem("Call forward when busy","718 + ext"))
        map.add(DummyItem("Call follow me","719 + ext"))
        map.add(DummyItem("Do not disturb","716 + ext"))
        map.add(DummyItem("Swap call","Flash + 6 ( In Speech )"))

        map.add(DummyItem("Feature cancellation","7900"))
        map.add(DummyItem("Hot line with extension","751 + 0/1 + ext"))
        map.add(DummyItem("Hot line with trunk","752 + 0/1 + trk"))
        map.add(DummyItem("Cancel hot line","7910"))
        map.add(DummyItem("Call pickup","*"))
        map.add(DummyItem("Call pickup other than trunk incoming","74"))

        map.add(DummyItem("Barge in","766 + ext"))
        map.add(DummyItem("Intrude","768 + ext"))
        map.add(DummyItem("Call privacy","715 + ext + 0/1"))
        map.add(DummyItem("Paging announcement","767"))
        map.add(DummyItem("Emergency extension number","70"))
        map.add(DummyItem("Trunk redial","611"))
        map.add(DummyItem("Direct fetch trunk","8+(1-3)"))

        map.add(DummyItem("Abbreviated dialing","62 + Code Number[00-99]"))
        map.add(DummyItem("Attending camped call","Flash + *85"))
        map.add(DummyItem("Conference","Flash + 7 ( In Speech )"))
        map.add(DummyItem("Auto call back ","72"))

        map.add(DummyItem("Call Parking","7jhjk2"))
        map.add(DummyItem("Parked Call Pickup","7jhjk2"))
        map.add(DummyItem("Parked Call Pickup from other Ext","7jhjk2"))
        map.add(DummyItem("Auto call back","72"))
        map.add(DummyItem("Reminder alarm","754 + HH + MM"))
        map.add(DummyItem("Remote extension reminder alarm","754 + * + ext + HH + MM"))
        map.add(DummyItem("Alarm cancellation","7920"))
        map.add(DummyItem("Manual Day Night Change Over ","75312 + (0 - D)/(1 – N)/(2 – C)"))

        return map
    }

    /**
     * A dummy item representing a piece of content.
     */
    @Parcelize
    data class DummyItem(val title: String, val value: String) : Parcelable {
        override fun toString(): String = title + value
    }
}
