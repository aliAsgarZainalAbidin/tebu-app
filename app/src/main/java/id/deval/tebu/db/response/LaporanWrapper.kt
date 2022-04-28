package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class LaporanWrapper<T>(
    @field:SerializedName("Report")
    var laporan:T?
)
