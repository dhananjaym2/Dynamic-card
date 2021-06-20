package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Card_groups(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("design_type") val design_type: String?,
    @SerializedName("cards") val cards: List<Cards>,
    @SerializedName("height") val height: Int = 0,
    @SerializedName("is_scrollable") val is_scrollable: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Cards)!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(design_type)
        parcel.writeTypedList(cards)
        parcel.writeInt(height)
        parcel.writeByte(if (is_scrollable) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card_groups> {
        override fun createFromParcel(parcel: Parcel): Card_groups {
            return Card_groups(parcel)
        }

        override fun newArray(size: Int): Array<Card_groups?> {
            return arrayOfNulls(size)
        }
    }
}