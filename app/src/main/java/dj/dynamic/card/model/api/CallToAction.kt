package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CallToAction(
    @SerializedName("text") val text: String?,
    @SerializedName("bg_color") val bg_color: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("text_color") val text_color: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeString(bg_color)
        parcel.writeString(url)
        parcel.writeString(text_color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CallToAction> {
        override fun createFromParcel(parcel: Parcel): CallToAction {
            return CallToAction(parcel)
        }

        override fun newArray(size: Int): Array<CallToAction?> {
            return arrayOfNulls(size)
        }
    }

}
