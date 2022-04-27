package com.demo.demotask.utils

import androidx.room.TypeConverter
import com.demo.demotask.local.model.Records
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecordsConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromString(value: String): List<Records> {
            val listType = object : TypeToken<List<Records>>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromListToString(list: List<Records>): String {
            val gson = Gson()
            return gson.toJson(list)
        }
    }

}