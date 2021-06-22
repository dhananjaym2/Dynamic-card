package dj.dynamic.card.view.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dj.dynamic.card.R
import dj.dynamic.card.model.api.Card_groups
import dj.dynamic.card.util.ui.recycler_view.HorizontalSpaceItemDecoration
import java.lang.ref.WeakReference

class VerticalRecyclerViewAdapter(
    activityContext: Activity, private var cardGroups: List<Card_groups>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val logTag: String = VerticalRecyclerViewAdapter::class.java.simpleName
    private var weakActivityContext: WeakReference<Activity> = WeakReference(activityContext)

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
        weakActivityContext.get()?.let { activityContext ->
            val itemDecoration = HorizontalSpaceItemDecoration(
                activityContext.resources.getDimension(R.dimen.spacing_between_vertical_items)
                    .toInt()
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