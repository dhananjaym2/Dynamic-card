package dj.dynamic.card.util.ui.recycler_view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(private val leftSpacing: Int, private val rightSpacing: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.left = leftSpacing
        outRect.right = rightSpacing
    }
}