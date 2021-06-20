package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Cards(
    @SerializedName("name") val name: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("formatted_title") val formatted_title: Formatted_title,
    @SerializedName("description") val description: String?,
    @SerializedName("formatted_description") val formatted_description: Formatted_description,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("url") val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable<Formatted_title>(Formatted_title::class.java.classLoader) as Formatted_title,
        parcel.readString(),
        parcel.readParcelable<Formatted_description>(Class.forName(Formatted_description::class.java.simpleName).classLoader) as Formatted_description,
        parcel.readParcelable<Icon>(Icon::class.java.classLoader) as Icon,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(title)
        parcel.writeParcelable(formatted_title, flags)
        parcel.writeString(description)
        parcel.writeParcelable(formatted_description, flags)
        parcel.writeParcelable(icon, flags)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cards> {
        override fun createFromParcel(parcel: Parcel): Cards {
            return Cards(parcel)
        }

        override fun newArray(size: Int): Array<Cards?> {
            return arrayOfNulls(size)
        }
    }
}