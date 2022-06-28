package id.deval.tebu.utils

object Constanta {
    const val ID_ITEM_ARGS = "id-item-args"
    const val PETAK = "petak"
    const val JENIS = "jenis"
    const val KATEGORI = "kategori"
    val headers = arrayOf(
        "Mandor",
        "Kebun",
        "PTK (petak)",
        "Luas (ha)",
        "Jenis Tebu",
        "KTG",
        "Jumlah Batang (btg)",
        "Tinggi Batang (m)",
        "Diameter Btg (cm)",
        "Berat/Meter (Kg)",
        "Ku/Ha",
        "Jumlah Tebu Taksasi"
    )
    val headersPdf = arrayOf(
        "Mandor",
        "Kebun",
        "PTK (petak)",
        "Luas (ha)",
        "Jenis Tebu",
        "KTG",
        "Faktor Leng",
        "JML BTG PER MTR (btg)",
        "JML BTG PER ROW (btg)",
        "JML BTG PER HA (btg)",
        "SAAT INI (m)",
        "SAAT TEBANG (m)",
        "Diameter Btg (cm)",
        "Berat/Meter (Kg)",
        "Hit (ton/ha)",
        "Pandangan (ton/ha)",
        "Per Ha (ton)",
        "Ton (kwintal)"
    )
    val jmlhBatang = arrayOf(
        "Faktor Leng",
        "JML BTG PER MTR",
        "JML BTG PER ROW",
        "JML BTG PER HA",
    )
    val tinggiBatang = arrayOf(
        "SAAT INI",
        "SAAT TEBANG",
    )
    val kuha = arrayOf(
        "Hit",
        "Pandangan",
    )
    val taksasi = arrayOf(
        "Per Ha",
        "Ton"
    )
}