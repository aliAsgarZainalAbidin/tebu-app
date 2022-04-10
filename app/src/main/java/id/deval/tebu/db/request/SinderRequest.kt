package id.deval.tebu.db.request

data class SinderRequest(
    val nama:String,
    val username:String,
    val password:String,
    val telepon:String,
    val alamat:String,
    val wilayah:String,
    val role: String = "sinder",
    val lokasi:String
)
