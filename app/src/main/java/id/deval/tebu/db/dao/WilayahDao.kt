package id.deval.tebu.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.deval.tebu.db.models.Wilayah

@Dao
interface WilayahDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(wilayah: Wilayah)

    @Query("SELECT * FROM wilayah")
    fun getAllWilayah(): LiveData<List<Wilayah>>
}