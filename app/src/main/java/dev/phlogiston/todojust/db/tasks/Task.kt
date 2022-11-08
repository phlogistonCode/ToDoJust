package dev.phlogiston.todojust.db.tasks

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kizitonwose.calendar.core.daysOfWeek
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Entity
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val date: LocalDate = LocalDate.now()
): Parcelable
