package dj.dynamic.card.util.ui.dimen

import android.content.Context
import android.util.TypedValue

class DimensionUtil {

    /**
     * Convert the provided dp value to pixels
     */
    fun convertDpToPixel(context: Context, dp: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        )
    }
}