package es.ua.eps.carkier

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints

class CustomDateValidator(private val fechasNoPermitidas: List<Long>) : CalendarConstraints.DateValidator {
    override fun isValid(date: Long): Boolean {
        return !fechasNoPermitidas.contains(date)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(fechasNoPermitidas)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CustomDateValidator> {
        override fun createFromParcel(parcel: Parcel): CustomDateValidator {
            val fechasNoPermitidas = mutableListOf<Long>()
            parcel.readList(fechasNoPermitidas, Long::class.java.classLoader)
            return CustomDateValidator(fechasNoPermitidas)
        }

        override fun newArray(size: Int): Array<CustomDateValidator?> {
            return arrayOfNulls(size)
        }
    }
}
