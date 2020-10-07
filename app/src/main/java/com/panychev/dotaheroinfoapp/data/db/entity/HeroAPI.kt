package com.panychev.dotaheroinfoapp.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val name = "Abathur"
@Entity(tableName = "table_hero_list")
data class HeroAPI(
    //val abilities: List<Ability>,
    @SerializedName("attribute_id")
    val attributeId: String,
    @SerializedName("icon_url")
    @Embedded(prefix = "icon_url_")
    val iconUrl: IconUrl,
    @PrimaryKey(autoGenerate = false)
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val role: String,
    @SerializedName("short_name")
    val shortName: String,
    //val talents: List<Talent>,
    val type: String
)