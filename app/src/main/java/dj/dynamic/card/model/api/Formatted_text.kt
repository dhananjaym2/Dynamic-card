package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Formatted_text(
    @SerializedName("text") val text: String?,
    @SerializedName("entities") val entities: List<Entities>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(Entities)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeTypedList(entities)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Formatted_text> {
        override fun createFromParcel(parcel: Parcel): Formatted_text {
            return Formatted_text(parcel)
        }

        override fun newArray(size: Int): Array<Formatted_text?> {
            return arrayOfNulls(size)
        }
    }
}