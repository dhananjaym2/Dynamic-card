package dj.dynamic.card.model.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Cards(
    @SerializedName("name") val name: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("formatted_title") val formatted_title: Formatted_text,
    @SerializedName("description") val description: String?,
    @SerializedName("formatted_description") val formatted_description: Formatted_text,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("url") val url: String?,
    @SerializedName("bg_image") val bg_image: Icon?,
    @SerializedName("bg_color") val bg_color: String?,
    @SerializedName("bg_gradient") val bg_gradient: Bg_gradient?,
    @SerializedName("cta") val cta: List<CallToAction>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable<Formatted_text>(Formatted_text::class.java.classLoader) as Formatted_text,
        parcel.readString(),
        parcel.readParcelable<Formatted_text>(Formatted_text::class.java.classLoader) as Formatted_text,
        parcel.readParcelable<Icon>(Icon::class.java.classLoader) as Icon,
        parcel.readString(),
        parcel.readParcelable<Icon>(Icon::class.java.classLoader) as Icon,
        parcel.readString(),
        parcel.readParcelable<Bg_gradient>(Bg_gradient::class.java.classLoader),
        parcel.createTypedArrayList(CallToAction)!!
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
        parcel.writeParcelable(bg_image, flags)
        parcel.writeString(bg_color)
        parcel.writeParcelable(bg_gradient, flags)
        parcel.writeTypedList(cta)
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