package com.freelancersworld.data.local.converter

import androidx.compose.runtime.Stable
import androidx.room.TypeConverter
import com.freelancersworld.utils.Utility.fromJson
import com.freelancersworld.utils.Utility.toJson

@Stable
class PhotosConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}