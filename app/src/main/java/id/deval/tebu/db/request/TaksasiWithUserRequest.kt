package id.deval.tebu.db.request

import com.google.gson.annotations.SerializedName

data class TaksasiWithUserRequest(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("nama")
    val nama:String,
    @field:SerializedName("wilayah")
    val wilayah:String,
    @field:SerializedName("lokasi")
    val lokasi:String,
    @field:SerializedName("petak")
    val petak:String,
    @field:SerializedName("nama_kebun")
    val namaKebun:String,
    @field:SerializedName("luas")
    val luas:String,
    @field:SerializedName("jenis_tebu")
    val jenis:String,
    @field:SerializedName("kategori")
    val kategori:String,
    @field:SerializedName("mandor")
    val mandor: String,
    @field:SerializedName("faktor_leng")
    val faktorLeng: String,
    @field:SerializedName("batang_per_meter")
    val batangPerMeter: String,
    @field:SerializedName("batang_per_row")
    val batangPerRow: String,
    @field:SerializedName("batang_per_ha")
    val batangPerHa: String,
    @field:SerializedName("tinggi_ini")
    val tinggiIni: String,
    @field:SerializedName("tinggi_tebang")
    val tinggiTebang: String,
    @field:SerializedName("diameter_batang")
    val diameterBatang: String,
    @field:SerializedName("berat_per_meter")
    val beratPerMeter: String,
    @field:SerializedName("hit")
    val hit: String,
    @field:SerializedName("pandangan")
    val pandangan: String,
    @field:SerializedName("per_hit")
    val perHit: String,
    @field:SerializedName("kui")
    val kui: String
)
