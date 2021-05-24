package dev.phlogiston.todojust.db.notes

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Note(
    @PrimaryKey
    val id: Int,
    val text: String
): Parcelable
