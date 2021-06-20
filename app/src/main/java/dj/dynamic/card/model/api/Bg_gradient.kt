package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class Bg_gradient(
    @SerializedName("colors") val colors: ArrayList<String>?,
    @SerializedName("angle") val angle: Float? = 0f
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList(),
        parcel.readValue(Float::class.java.classLoader) as? Float
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(colors)
        parcel.writeValue(angle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bg_gradient> {
        override fun createFromParcel(parcel: Parcel): Bg_gradient {
            return Bg_gradient(parcel)
        }

        override fun newArray(size: Int): Array<Bg_gradient?> {
            return arrayOfNulls(size)
        }
    }

}
