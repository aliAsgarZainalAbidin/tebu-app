package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class GlobalWrapperResponse<T>(
    @field:SerializedName("status")
    val status:String,

    @field:SerializedName("data")
    val data: T?,
)
