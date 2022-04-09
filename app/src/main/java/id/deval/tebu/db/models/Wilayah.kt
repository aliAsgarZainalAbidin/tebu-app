package id.deval.tebu.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wilayah(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val idWilayah: String,
    val namaWilayah: String,
    val namaRayon: String,
    val lokasi:String
)
