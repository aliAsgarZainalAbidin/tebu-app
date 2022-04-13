package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("id")
    val id:String,

    @field:SerializedName("nama")
    val nama:String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("role")
    val role:String,

    @field:SerializedName("telepon")
    val telepon:String?,

    @field:SerializedName("alamat")
    val alamat:String?,

    @field:SerializedName("wilayah")
    val wilayah:String,

    @field:SerializedName("lokasi")
    val lokasi:String,

    @field:SerializedName("token")
    val token:String?
)
