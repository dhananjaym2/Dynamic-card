package dj.dynamic.card.view.adapter

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import dj.dynamic.card.R
import dj.dynamic.card.constant.DesignTypeConstants.BIG_DISPLAY_CARD_HC3
import dj.dynamic.card.constant.DesignTypeConstants.DYNAMIC_WIDTH_CARD_HC9
import dj.dynamic.card.constant.DesignTypeConstants.IMAGE_CARD_HC5
import dj.dynamic.card.constant.DesignTypeConstants.SMALL_CARD_WITH_ARROW_HC6
import dj.dynamic.card.constant.DesignTypeConstants.SMALL_DISPLAY_CARD_HC1
import dj.dynamic.card.model.api.CallToAction
import dj.dynamic.card.model.api.Card_groups
import dj.dynamic.card.model.api.Cards
import dj.dynamic.card.util.ui.dimen.DimensionUtil
import dj.dynamic.card.util.ui.image.ImageUtils
import java.lang.ref.WeakReference

class HorizontalRecyclerView(activityContext: Activity, var cardGroups: Card_groups) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val logTag: String = VerticalRecyclerViewAdapter::class.java.simpleName
    private var cardGroupsList: List<Cards> = cardGroups.cards
    private var weakActivityContext: WeakReference<Activity?> = WeakReference(activityContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(weakActivityContext.get())
        when (viewType) {//inflate different layout based on this value
            DesignTypeEnum.SMALL_DISPLAY_CARD_HC1.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.small_display_card_hc1, parent, false)
                return SmallDisplayCardHC1(itemView)
            }
            DesignTypeEnum.BIG_DISPLAY_CARD_HC3.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.big_display_card_hc3, parent, false)
                return BigDisplayCardHC3(itemView)
            }
            DesignTypeEnum.IMAGE_CARD_HC5.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.image_card_hc5, parent, false)
                return ImageCardHC5(itemView)
            }
            DesignTypeEnum.SMALL_CARD_WITH_ARROW_HC6.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.small_card_with_arrow_hc6, parent, false)
                return SmallCardWithArrowHc6(itemView)
            }
            DesignTypeEnum.DYNAMIC_WIDTH_CARD_HC9.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.dynamic_width_card_hc9, parent, false)
                return DynamicWidthCardHC9(itemView)
            }
            else -> {
                Log.e(logTag, "DesignType is UNKNOWN can't show data for this.")
                val itemView: View =
                    inflater.inflate(R.layout.unknown_card, parent, false)
                return UnknownCard(itemView)
            }
        }
    }

    override fun getItemCount(): Int {
        return cardGroupsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (cardGroups.design_type) {
            "HC1" -> {
                val viewHolder = holder as SmallDisplayCardHC1
                loadImageFromUrl(
                    cardGroupsList[position].icon?.image_url ?: "", viewHolder.leftImageInHC1, 1.0
                )
                //TODO read and show formatted text
                viewHolder.titleTextHC1.text = cardGroupsList[position].title
                if (!TextUtils.isEmpty(cardGroupsList[position].description)) {
                    viewHolder.descriptionTextHC1.visibility = View.VISIBLE
                    viewHolder.descriptionTextHC1.text = cardGroupsList[position].description
                } else {
                    viewHolder.descriptionTextHC1.visibility = View.GONE
                }
                weakActivityContext.get()?.let { activity ->
                    viewHolder.rootLayoutHC1.backgroundTintList =
                        getDrawableWithTint(activity, cardGroupsList[position].bg_color)
                }
                viewHolder.rootLayoutHC1.layoutParams.width =
                    if (cardGroups.is_scrollable) ViewGroup.LayoutParams.WRAP_CONTENT
                    else ViewGroup.LayoutParams.MATCH_PARENT
            }
            "HC3" -> {
                val viewHolder = holder as BigDisplayCardHC3
                loadImageFromUrl(
                    cardGroupsList[position].icon?.image_url ?: "",
                    viewHolder.leftTopImageInHC3,
                    1.0
                )
                viewHolder.titleTextHC3.text = cardGroupsList[position].title
                viewHolder.descriptionTextHC3.text = cardGroupsList[position].description
                for (buttonAction: CallToAction in cardGroupsList[position].cta!!) {
//                    viewHolder.actionButtonHC3.textColors = buttonAction.text_color
                    viewHolder.actionButtonHC3.text = buttonAction.text
                    weakActivityContext.get()?.let { activity ->
                        viewHolder.actionButtonHC3.backgroundTintList =
                            getDrawableWithTint(activity, buttonAction.bg_color)
                    }
                }
                loadImageFromUrl(
                    cardGroupsList[position].bg_image?.image_url,
                    viewHolder.backgroundImageViewHC3,
                    1.0
                )
                viewHolder.rootLayoutHC3.setBackgroundColor(
                    getColorInt(cardGroupsList[position].bg_color)
                )
            }
            "HC5" -> {
                val viewHolder = holder as ImageCardHC5
                loadImageFromUrl(
                    cardGroupsList[position].icon?.image_url ?: "",
                    viewHolder.leftImageInHC5,
                    1.0
                )
                viewHolder.titleTextHC5.text = cardGroupsList[position].title
                viewHolder.descriptionTextHC5.text = cardGroupsList[position].description
//                weakActivityContext.get()?.let { activity ->
//                    viewHolder.rootLayoutHC5.backgroundTintList =
//                        getDrawableWithTint(activity, cardGroupsList[position].bg_image?.image_url)
//                }
                loadImageFromUrl(
                    cardGroupsList[position].bg_image?.image_url,
                    viewHolder.backgroundImageViewHC5,
                    1.0
                )
                viewHolder.coloredRectangleHC5.setBackgroundColor(
                    getColorInt(cardGroupsList[position].bg_color)
                )
            }
            "HC6" -> {
                val viewHolder = holder as SmallCardWithArrowHc6
                loadImageFromUrl(
                    cardGroupsList[position].icon?.image_url ?: "",
                    viewHolder.imageInSmallCardWithArrow,
                    1.0
                )
                viewHolder.textSmallCardWithArrow.text = cardGroupsList[position].title
                weakActivityContext.get()?.let { activity ->
                    viewHolder.rootLayoutHC6.backgroundTintList =
                        getDrawableWithTint(activity, cardGroupsList[position].bg_color)
                }
            }
            "HC9" -> {
                val viewHolder = holder as DynamicWidthCardHC9
                weakActivityContext.get()?.let {
                    viewHolder.backgroundImageViewHC9.layoutParams.width =
                        DimensionUtil().convertDpToPixel(
                            it,
                            (cardGroupsList[position].bg_image?.aspect_ratio?.times(cardGroups.height))?.toInt()
                                ?: 100
                        ).toInt()

                    viewHolder.backgroundImageViewHC9.layoutParams.height =
                        DimensionUtil().convertDpToPixel(it, cardGroups.height).toInt()
                }
                loadImageFromUrl(
                    cardGroupsList[position].bg_image?.image_url,
                    viewHolder.backgroundImageViewHC9,
                    cardGroupsList[position].bg_image?.aspect_ratio
                )
            }
        }
    }

    private fun getColorInt(bgColor: String?): Int {
        return if (!TextUtils.isEmpty(bgColor)) {
            Color.parseColor(bgColor)
        } else {// default
            R.color.white
        }
    }

    private fun getDrawableWithTint(context: Context, color: String?): ColorStateList? {
        return if (!TextUtils.isEmpty(color)) {
            @ColorInt val colorInt: Int = Color.parseColor(color)
            ColorStateList(arrayOf(intArrayOf()), intArrayOf(colorInt))
        } else {// default
            ColorStateList(
                arrayOf(intArrayOf()),
                intArrayOf(ContextCompat.getColor(context, R.color.white))
            )
        }
    }

    private fun loadImageFromUrl(
        imageUrl: String?,
        imageView: ImageView,
        aspect_ratio: Double?
    ) {
        if (TextUtils.isEmpty(imageUrl)) {// remove the existing bitmap if image is null or empty
            imageView.visibility = View.GONE
            imageView.setImageDrawable(null)
        } else {
            imageView.visibility = View.VISIBLE
            val target: Target = object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: LoadedFrom) {
                    imageView.setImageDrawable(
                        weakActivityContext.get()?.let { activity ->
                            ImageUtils.getRoundedBitmapDrawable(
                                bitmap,
                                activity.resources?.getDimension(R.dimen.card_corner_radius),
                                activity
                            )
                        }
                    )
                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
            }
            Picasso.get().load(imageUrl).placeholder(R.drawable.ic_outline_image_24)
                .error(R.drawable.ic_baseline_warning_24).into(target)
        }
    }

    class SmallDisplayCardHC1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootLayoutHC1: RelativeLayout = itemView.findViewById(R.id.rootLayoutHC1)
        var leftImageInHC1: ImageView = itemView.findViewById(R.id.leftImageInHC1)
        var titleTextHC1: TextView = itemView.findViewById(R.id.titleTextHC1)
        var descriptionTextHC1: TextView = itemView.findViewById(R.id.descriptionTextHC1)
    }

    class BigDisplayCardHC3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootLayoutHC3: RelativeLayout = itemView.findViewById(R.id.rootLayoutHC3)
        var backgroundImageViewHC3: ImageView = itemView.findViewById(R.id.backgroundImageViewHC3)
        var leftTopImageInHC3: ImageView = itemView.findViewById(R.id.backgroundImageViewHC3)
        var titleTextHC3: TextView = itemView.findViewById(R.id.titleTextHC3)
        var descriptionTextHC3: TextView = itemView.findViewById(R.id.descriptionTextHC3)

        //TODO array of buttons
        var actionButtonHC3: Button = itemView.findViewById(R.id.actionButtonHC3)
    }

    class ImageCardHC5(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootLayoutHC5: RelativeLayout = itemView.findViewById(R.id.rootLayoutHC5)
        var backgroundImageViewHC5: ImageView = itemView.findViewById(R.id.backgroundImageViewHC5)
        var leftImageInHC5: ImageView = itemView.findViewById(R.id.leftImageInHC5)
        var titleTextHC5: TextView = itemView.findViewById(R.id.titleTextHC5)
        var descriptionTextHC5: TextView = itemView.findViewById(R.id.descriptionTextHC5)
        var coloredRectangleHC5: View = itemView.findViewById(R.id.coloredRectangleHC5)
    }

    class SmallCardWithArrowHc6(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootLayoutHC6: RelativeLayout = itemView.findViewById(R.id.rootLayoutHC6)
        var imageInSmallCardWithArrow: ImageView =
            itemView.findViewById(R.id.imageInSmallCardWithArrow)
        var textSmallCardWithArrow: TextView =
            itemView.findViewById(R.id.textSmallCardWithArrow)
    }

    class DynamicWidthCardHC9(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val backgroundImageViewHC9: ImageView = itemView.findViewById(R.id.backgroundImageViewHC9)
    }

    class UnknownCard(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        when (cardGroups.design_type) {
            SMALL_DISPLAY_CARD_HC1 -> {
                return DesignTypeEnum.SMALL_DISPLAY_CARD_HC1.ordinal
            }
            BIG_DISPLAY_CARD_HC3 -> {
                return DesignTypeEnum.BIG_DISPLAY_CARD_HC3.ordinal
            }
            IMAGE_CARD_HC5 -> {
                return DesignTypeEnum.IMAGE_CARD_HC5.ordinal
            }
            SMALL_CARD_WITH_ARROW_HC6 -> {
                return DesignTypeEnum.SMALL_CARD_WITH_ARROW_HC6.ordinal
            }
            DYNAMIC_WIDTH_CARD_HC9 -> {
                return DesignTypeEnum.DYNAMIC_WIDTH_CARD_HC9.ordinal
            }
            else -> {
                Log.w(logTag, "design_type of card is unknown at $position in getItemViewType().")
                return DesignTypeEnum.UNKNOWN_CARD.ordinal
            }
        }
    }

    private enum class DesignTypeEnum {
        SMALL_DISPLAY_CARD_HC1,
        BIG_DISPLAY_CARD_HC3,
        IMAGE_CARD_HC5,
        SMALL_CARD_WITH_ARROW_HC6,
        DYNAMIC_WIDTH_CARD_HC9,

        UNKNOWN_CARD
    }
}