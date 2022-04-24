package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class KebunWrapper<T>(
    @field:SerializedName("Kebun")
    val kebun : T
)
