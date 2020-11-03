package com.panychev.dotaheroinfoapp.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.panychev.dotaheroinfoapp.data.network.response.Talent

class TypeConverterTalentList {
    @TypeConverter
    fun toJsonString(talents: List<Talent>): String{
        return Gson().toJson(talents)
    }

    @TypeConverter
    fun toTalentList(jsonString: String): List<Talent>{
        val listType = object : TypeToken<List<Talent>>(){}.type
        return Gson().fromJson(jsonString, listType)
    }
}