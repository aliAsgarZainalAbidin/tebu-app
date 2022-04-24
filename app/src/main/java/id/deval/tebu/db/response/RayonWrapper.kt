package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class RayonWrapper<T>(
    @field:SerializedName("Rayon")
    val rayon : T
)
