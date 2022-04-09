package id.deval.tebu.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.deval.tebu.db.dao.WilayahDao
import id.deval.tebu.db.models.Wilayah

@Database(entities = [Wilayah::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun wilayahDao(): WilayahDao
}