package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("image_type") val image_type: String?,
    @SerializedName("asset_type") val asset_type: String?,
    @SerializedName("aspect_ratio") val aspect_ratio: Double = 1.0,
    @SerializedName("image_url") val image_url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image_type)
        parcel.writeString(asset_type)
        parcel.writeDouble(aspect_ratio)
        parcel.writeString(image_url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Icon> {
        override fun createFromParcel(parcel: Parcel): Icon {
            return Icon(parcel)
        }

        override fun newArray(size: Int): Array<Icon?> {
            return arrayOfNulls(size)
        }
    }
}