package id.deval.tebu.utils

object Constanta {
    const val ID_ITEM_ARGS = "id-item-args"
    const val PETAK = "petak"
    const val JENIS = "jenis"
    const val KATEGORI = "kategori"
    val headers = arrayOf(
        "Mandor",
        "Kebun",
        "PTK",
        "Luas",
        "Jenis Tebu",
        "KTG",
        "Jumlah Batang",
        "Tinggi Batang",
        "Diameter Btg",
        "Berat/Meter",
        "Ku/Ha",
        "Jumlah Tebu Taksasi"
    )
    val headersPdf = arrayOf(
        "Mandor",
        "Kebun",
        "PTK",
        "Luas",
        "Jenis Tebu",
        "KTG",
        "Faktor Leng",
        "JML BTG PER MTR",
        "JML BTG PER ROW",
        "JML BTG PER HA",
        "SAAT INI",
        "SAAT TEBANG",
        "Diameter Btg",
        "Berat/Meter",
        "Hit",
        "Pandangan",
        "Per Ha",
        "Ton"
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