package dj.dynamic.card.view.adapter

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dj.dynamic.card.constant.DesignType
import dj.dynamic.card.model.api.Card_groups

class VerticalRecyclerViewAdapter(activityContext: Activity, var cardGroups: List<Card_groups>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val logTag: String = VerticalRecyclerViewAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView: View
        when (viewType) {
            DesignType.SMALL_DISPLAY_CARD_HC1.ordinal -> {
                //TODO create run time RV
            }
            DesignType.BIG_DISPLAY_CARD_HC3.ordinal -> {

            }
            DesignType.IMAGE_CARD_HC5.ordinal -> {

            }
            DesignType.SMALL_CARD_WITH_ARROW_HC6.ordinal -> {

            }
            DesignType.DYNAMIC_WIDTH_CARD_HC9.ordinal -> {

            }
            DesignType.UNKNOWN.ordinal -> {
                Log.e(logTag, "DesignType.UNKNOWN can't show data at $viewType.")

            }
        }
        return itemView
    }

    override fun getItemCount(): Int {
        return cardGroups.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
//        when (cardGroups[position].design_type) {
//            "HC1" -> {
//                return DesignType.SMALL_DISPLAY_CARD_HC1.ordinal
//            }
//            "HC3" -> {
//                return DesignType.BIG_DISPLAY_CARD_HC3.ordinal
//            }
//            "HC5" -> {
//                return DesignType.IMAGE_CARD_HC5.ordinal
//            }
//            "HC6" -> {
//                return DesignType.SMALL_CARD_WITH_ARROW_HC6.ordinal
//            }
//            "HC9" -> {
//                return DesignType.DYNAMIC_WIDTH_CARD_HC9.ordinal
//            }
//            else -> {
//                Log.e(logTag, "DesignType.UNKNOWN can't show data at $position.")
//                return DesignType.UNKNOWN.ordinal
//            }
//        }
    }

    override fun getItemViewType(position: Int): Int {
        when (cardGroups[position].design_type) {
            "HC1" -> {
                return DesignType.SMALL_DISPLAY_CARD_HC1.ordinal
            }
            "HC3" -> {
                return DesignType.BIG_DISPLAY_CARD_HC3.ordinal
            }
            "HC5" -> {
                return DesignType.IMAGE_CARD_HC5.ordinal
            }
            "HC6" -> {
                return DesignType.SMALL_CARD_WITH_ARROW_HC6.ordinal
            }
            "HC9" -> {
                return DesignType.DYNAMIC_WIDTH_CARD_HC9.ordinal
            }
            else -> {
                Log.e(logTag, "DesignType.UNKNOWN can't show data at $position.")
                return DesignType.UNKNOWN.ordinal
            }
        }
    }

    class SmallDisplayCardVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}