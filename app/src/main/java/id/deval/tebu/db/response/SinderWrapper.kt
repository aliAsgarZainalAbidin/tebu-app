package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class SinderWrapper<T>(
    @field:SerializedName("Sinder")
    val sinder : T
)
