package com.demo.demotask.local.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MandiDataResponse(
    @SerializedName("created")
    val created: Long,
    @SerializedName("updated")
    val updated: Long,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("updated_date")
    val updatedDate: String,
    @SerializedName("active")
    val active: String,
    @SerializedName("index_name")
    val indexName: String,
    @SerializedName("org")
    val org: List<String>,
    @SerializedName("org_type")
    val orgType: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("external_ws_url")
    val externalWsUrl: String,
    @SerializedName("visualizable")
    val visualizable: String,
    @SerializedName("external_ws")
    val externalWs: Int,
    @SerializedName("catalog_uuid")
    val catalogUuid: String,
    @SerializedName("sector")
    val sector: List<String>,
    @SerializedName("desc")
    val desc: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("version")
    val version: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: String,
    @SerializedName("offset")
    val offset: String,
    @SerializedName("field")
    val field: List<Field>,
    @SerializedName("target_bucket")
    val targetBucket: TargetBucket,
    @SerializedName("records")
    val records: List<Records>
){


    data class Field(
        @SerializedName("name")
        val name: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("type")
        val type: String
    )

    data class TargetBucket(
        @SerializedName("field")
        val field: String,
        @SerializedName("index")
        val index: String,
        @SerializedName("type")
        val type: String
    )


}
