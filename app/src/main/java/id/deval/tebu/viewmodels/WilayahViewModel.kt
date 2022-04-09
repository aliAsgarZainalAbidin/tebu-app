package id.deval.tebu.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.models.Wilayah
import id.deval.tebu.fragment.wilayah.WilayahRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WilayahViewModel @Inject constructor(
    private val wilayahRepository: WilayahRepository
) : ViewModel() {
    val status: MutableLiveData<Boolean> = MutableLiveData()

    fun save(wilayah: Wilayah){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                wilayahRepository.saveWilayah(wilayah)
                status.postValue(true)
                Log.d("TAG", "save: SUCCESFULLY")
            }
        }
    }
}