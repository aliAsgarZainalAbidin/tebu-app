package id.deval.tebu.db.request

import com.google.gson.annotations.SerializedName

data class RayonRequest(
    @field:SerializedName("id")
    val id :String?,

    @field:SerializedName("nama_rayon")
    val nama:String,

    @field:SerializedName("nama_lokasi")
    val lokasi:String
)
