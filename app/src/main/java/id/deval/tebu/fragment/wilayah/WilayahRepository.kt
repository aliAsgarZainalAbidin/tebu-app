package id.deval.tebu.fragment.wilayah

import id.deval.tebu.db.models.Wilayah
import javax.inject.Inject
import javax.inject.Singleton
import id.deval.tebu.db.Database


@Singleton
class WilayahRepository @Inject constructor(database: Database) {
    private val wilayahDao = database.wilayahDao()

    fun saveWilayah(wilayah: Wilayah) {
        wilayahDao.insert(wilayah)
    }
}