package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CardGroupApiResponse(
    @SerializedName("card_groups") val card_groups: List<Card_groups>
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Card_groups)!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(card_groups)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardGroupApiResponse> {
        override fun createFromParcel(parcel: Parcel): CardGroupApiResponse {
            return CardGroupApiResponse(parcel)
        }

        override fun newArray(size: Int): Array<CardGroupApiResponse?> {
            return arrayOfNulls(size)
        }
    }
}