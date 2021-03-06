package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Entities(
    @SerializedName("text") val text: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("font_style") val font_style: String?
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
        parcel.writeString(color)
        parcel.writeString(url)
        parcel.writeString(font_style)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Entities> {
        override fun createFromParcel(parcel: Parcel): Entities {
            return Entities(parcel)
        }

        override fun newArray(size: Int): Array<Entities?> {
            return arrayOfNulls(size)
        }
    }
}