package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class Kebun(
    @field:SerializedName("id")
    val id:String,
    @field:SerializedName("nama_kebun")
    val namaKebun: String,
    @field:SerializedName("luas")
    val luas: String,
    @field:SerializedName("petak")
    val petak: String,
    @field:SerializedName("jenis_tebu")
    val jenisTebu: String,
    @field:SerializedName("kategori")
    val kategori: String,
    @field:SerializedName("nama_sinder")
    val namaSinder: String,
    @field:SerializedName("wilayah")
    val wilayah: String
)
