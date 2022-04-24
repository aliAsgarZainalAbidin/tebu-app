package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class WilayahWrapper<T>(
    @field:SerializedName("Wilayah")
    val wilayah : T
)
