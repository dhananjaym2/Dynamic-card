package dj.dynamic.card.util.ui.image

import android.app.Activity
import android.graphics.Bitmap
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

object ImageUtils {

    fun getRoundedBitmapDrawable(
        bitmap: Bitmap?, radius: Float?,
        context: Activity
    ): RoundedBitmapDrawable? {
        val drawable = RoundedBitmapDrawableFactory.create(context.resources, bitmap)
        drawable.cornerRadius = radius ?: 0f
        return drawable
    }
}