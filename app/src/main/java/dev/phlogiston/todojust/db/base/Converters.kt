package dev.phlogiston.todojust.db.base

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun toListString(data: String?): List<String> =
        gson.fromJson(data, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun fromListString(data: List<String>?): String =
        gson.toJson(data)

    @TypeConverter
    fun toListInt(data: String?): List<Int> =
        gson.fromJson(data, object : TypeToken<List<Int>>() {}.type)

    @TypeConverter
    fun fromListInt(data: List<Int>?): String =
        gson.toJson(data)

    @TypeConverter
    fun toStringFromMap(value: Map<String, String>?): String =
        gson.toJson(value)

    @TypeConverter
    fun fromMapToString(data: String?): Map<String, String>? =
        gson.fromJson(data,  object : TypeToken<Map<String, String>>() {}.type)

    @TypeConverter
    fun toTimestamp(value: Long?): Date? =
        value?.let { Date(it) }

    @TypeConverter
    fun fromTimestamp(date: Date?): Long? =
        date?.time

}