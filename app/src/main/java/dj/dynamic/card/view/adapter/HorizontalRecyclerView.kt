package dj.dynamic.card.view.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dj.dynamic.card.R
import dj.dynamic.card.constant.DesignType
import dj.dynamic.card.model.api.Card_groups
import dj.dynamic.card.model.api.Cards
import java.lang.ref.WeakReference

class HorizontalRecyclerView(activityContext: Activity, var cardGroups: Card_groups) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val logTag: String = VerticalRecyclerViewAdapter::class.java.simpleName
    private var cardGroupsList: List<Cards> = cardGroups.cards
    private var weakActivityContext: WeakReference<Activity?> = WeakReference(activityContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(weakActivityContext.get())
        when (viewType) {//inflate different layout based on this value
            DesignType.SMALL_DISPLAY_CARD_HC1.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.small_display_card_hc1, parent, false)
                return SmallDisplayCardHC1(itemView)
            }
            DesignType.BIG_DISPLAY_CARD_HC3.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.big_display_card_hc3, parent, false)
                return BigDisplayCardHC3(itemView)
            }
            DesignType.IMAGE_CARD_HC5.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.image_card_hc5, parent, false)
                return ImageCardHC5(itemView)
            }
            DesignType.SMALL_CARD_WITH_ARROW_HC6.ordinal -> {
                val itemView: View =
                    inflater.inflate(R.layout.small_card_with_arrow_hc6, parent, false)
                return SmallCardWithArrowHc6(itemView)
            }
            DesignType.DYNAMIC_WIDTH_CARD_HC9.ordinal -> {
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
        when (cardGroups.design_type) {//TODO read and show formatted text
            "HC1" -> {
                val viewHolder = holder as SmallDisplayCardHC1
                loadImageFromUrl(
                    cardGroupsList[position].icon.image_url ?: "", viewHolder.leftImageInHC1
                )
                viewHolder.titleTextHC1.text = cardGroupsList[position].title
                viewHolder.descriptionTextHC1.text = cardGroupsList[position].title
            }
            "HC6" -> {
                val viewHolder = holder as SmallCardWithArrowHc6
                loadImageFromUrl(
                    cardGroupsList[position].icon.image_url ?: "",
                    viewHolder.imageInSmallCardWithArrow
                )
                viewHolder.textSmallCardWithArrow.text = cardGroupsList[position].title
            }
        }
    }

    private fun loadImageFromUrl(imageUrl: String, imageView: ImageView) {
        Picasso.get().load(imageUrl).placeholder(R.drawable.ic_outline_image_24)
            .error(R.drawable.ic_baseline_warning_24).into(imageView)
    }

    class SmallDisplayCardHC1(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var leftImageInHC1: ImageView =
            itemView.findViewById(R.id.leftImageInHC1)
        var titleTextHC1: TextView = itemView.findViewById(R.id.titleTextHC1)
        var descriptionTextHC1: TextView = itemView.findViewById(R.id.descriptionTextHC1)
    }

    class BigDisplayCardHC3(itemView: View) : RecyclerView.ViewHolder(itemView) {//TODO
    }

    class ImageCardHC5(itemView: View) : RecyclerView.ViewHolder(itemView) {//TODO
    }

    class SmallCardWithArrowHc6(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageInSmallCardWithArrow: ImageView =
            itemView.findViewById(R.id.imageInSmallCardWithArrow)
        var textSmallCardWithArrow: TextView =
            itemView.findViewById(R.id.textSmallCardWithArrow)
    }

    class DynamicWidthCardHC9(itemView: View) : RecyclerView.ViewHolder(itemView) {//TODO
    }

    class UnknownCard(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun getItemViewType(position: Int): Int {
        when (cardGroups.design_type) {
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

    private fun getLayoutFile(design_type: String?): Int {
        when (design_type) {
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
                return R.layout.small_card_with_arrow_hc6
            }
            "HC9" -> {
                return DesignType.DYNAMIC_WIDTH_CARD_HC9.ordinal
            }
            else -> {
                Log.e(logTag, "DesignType.UNKNOWN can't show data for $design_type.")
                return DesignType.UNKNOWN.ordinal
            }
        }
    }
}

