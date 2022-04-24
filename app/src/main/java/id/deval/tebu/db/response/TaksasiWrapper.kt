package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class TaksasiWrapper<T>(
    @field:SerializedName("Taksasi")
    var taksasi:T
)
