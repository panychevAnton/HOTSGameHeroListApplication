package com.panychev.dotaheroinfoapp.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.panychev.dotaheroinfoapp.data.network.response.IconUrl
import com.panychev.dotaheroinfoapp.data.network.response.Talent

@Entity(tableName = "table_heroes", indices = [Index(value = ["name"], unique = true)])
data class Hero(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("attribute_id")
    val attributeId: String,
    @SerializedName("icon_url")
    @Embedded(prefix = "icon_url_")
    val iconUrl: IconUrl,
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val role: String,
    @SerializedName("short_name")
    val shortName: String,
    val type: String,
    val talents: List<Talent>
)