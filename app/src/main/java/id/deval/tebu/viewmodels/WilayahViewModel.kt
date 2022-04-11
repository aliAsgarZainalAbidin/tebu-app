package id.deval.tebu.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.response.MessageResponse
import id.deval.tebu.db.response.Wilayah
import id.deval.tebu.fragment.wilayah.WilayahRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WilayahViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val status: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var mutableListWilayah : MutableLiveData<ArrayList<Wilayah>>
    lateinit var mutableliveMessageResponse: MutableLiveData<MessageResponse>

    fun getAllWilayah(token:String):LiveData<ArrayList<Wilayah>>{
        mutableListWilayah = MutableLiveData()
        GlobalScope.launch {
            val listWilayah = repository.getAllWilayah(token)
            mutableListWilayah.postValue(listWilayah)
        }
        return mutableListWilayah
    }

    fun addWilayah(token: String, wilayah: Wilayah):LiveData<MessageResponse>{
        mutableliveMessageResponse = MutableLiveData()
        GlobalScope.launch {
            val message = repository.addWilayah(wilayah, token)
            mutableliveMessageResponse.postValue(message)
        }
        return mutableliveMessageResponse
    }
}