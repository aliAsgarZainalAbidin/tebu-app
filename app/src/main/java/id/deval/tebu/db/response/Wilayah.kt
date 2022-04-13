package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class Wilayah(
    @field:SerializedName("id")
    val id:String?,

    @field:SerializedName("nama_wilayah")
    val wilayah:String,

    @field:SerializedName("nama_rayon")
    val rayon:String,

    @field:SerializedName("nama_lokasi")
    val lokasi:String
)
