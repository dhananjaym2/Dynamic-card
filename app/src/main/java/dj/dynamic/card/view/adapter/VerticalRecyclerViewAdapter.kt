package dj.dynamic.card.view.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import dj.dynamic.card.R
import dj.dynamic.card.constant.DesignTypeConstants.BIG_DISPLAY_CARD_HC3
import dj.dynamic.card.constant.DesignTypeConstants.IMAGE_CARD_HC5
import dj.dynamic.card.constant.DesignTypeConstants.SMALL_CARD_WITH_ARROW_HC6
import dj.dynamic.card.constant.DesignTypeConstants.SMALL_DISPLAY_CARD_HC1
import dj.dynamic.card.model.api.Card_groups
import dj.dynamic.card.util.ui.recycler_view.HorizontalSpaceItemDecoration
import java.lang.ref.WeakReference

class VerticalRecyclerViewAdapter(
    activityContext: Activity, private var cardGroups: List<Card_groups>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val logTag: String = VerticalRecyclerViewAdapter::class.java.simpleName
    private var weakActivityContext: WeakReference<Activity> = WeakReference(activityContext)
    private val fullScreenWidthCardTypes = arrayOf(
        SMALL_DISPLAY_CARD_HC1, BIG_DISPLAY_CARD_HC3,
        IMAGE_CARD_HC5, SMALL_CARD_WITH_ARROW_HC6
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(weakActivityContext.get())
            .inflate(R.layout.vertical_row_item, parent, false)
        return VerticalViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardGroups.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder as VerticalViewHolder
        Log.v(logTag, "onBindViewHolder at $position")
        view.horizontalRecyclerView.layoutParams.height =
            if (cardGroups[position].height != 0) {
                cardGroups[position].height
            } else {
                ViewGroup.LayoutParams.WRAP_CONTENT
            }

        view.horizontalRecyclerView.layoutManager =
            LinearLayoutManager(weakActivityContext.get(), LinearLayoutManager.HORIZONTAL, false)

        /**
         * If the current cardGroups item is_scrollable is false and it's design type allows full
         * screen width then use a View pager like swipe gesture and animation.
         */
        if (!cardGroups[position].is_scrollable &&
            fullScreenWidthCardTypes.contains(cardGroups[position].design_type)
        ) {
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(view.horizontalRecyclerView)
        }

        weakActivityContext.get()?.let { activityContext ->
            val spacingBetweenIndividualHorizontalCards =
                activityContext.resources.getDimension(R.dimen.spacing_between_vertical_items)
                    .toInt()

            /**
             * If the current cardGroups item is_scrollable is true then the right side spacing is not required.
             * Whereas left side spacing is required irrespective of is_scrollable.
             */
            //TODO in case of HC5 irrespective of is_scrollable right padding should be there
            val itemDecoration =
                HorizontalSpaceItemDecoration(
                    spacingBetweenIndividualHorizontalCards,
                    if (cardGroups[position].is_scrollable) 0
                    else spacingBetweenIndividualHorizontalCards
                )
            view.horizontalRecyclerView.addItemDecoration(itemDecoration)

            val horizontalAdapter = HorizontalRecyclerView(activityContext, cardGroups[position])
            view.horizontalRecyclerView.adapter = horizontalAdapter
            Log.v(logTag, "onBindViewHolder at $position after the adapter is set.")
        }
    }

    class VerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var horizontalRecyclerView: RecyclerView =
            itemView.findViewById(R.id.horizontalRecyclerView)
    }

}