package com.demo.demotask.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.PropertyKey

@Entity(tableName = "MandiRecords")
data class Records(
    @Expose(serialize = false)
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("market")
    val market: String,
    @SerializedName("commodity")
    val commodity: String,
    @SerializedName("variety")
    val variety: String,
    @SerializedName("arrival_date")
    val arrivalDate: String,
    @SerializedName("min_price")
    val minPrice: String,
    @SerializedName("max_price")
    val maxPrice: String,
    @SerializedName("modal_price")
    val modalPrice: String
)