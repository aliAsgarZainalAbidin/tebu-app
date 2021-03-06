package id.deval.tebu.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.response.GlobalWrapperResponse
import id.deval.tebu.db.response.MessageResponse
import id.deval.tebu.db.response.Wilayah
import id.deval.tebu.db.response.WilayahWrapper
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
    lateinit var mutableListWilayah : MutableLiveData<GlobalWrapperResponse<WilayahWrapper<ArrayList<Wilayah>>>>
    lateinit var mutableliveMessageResponse: MutableLiveData<MessageResponse>
    lateinit var mutableliveMessageResponseDelete: MutableLiveData<MessageResponse>
    lateinit var mutableWilayah : MutableLiveData<GlobalWrapperResponse<WilayahWrapper<Wilayah>>>
    lateinit var mutableWilayahUpdated : MutableLiveData<Wilayah>

    fun getAllWilayah(token:String):LiveData<GlobalWrapperResponse<WilayahWrapper<ArrayList<Wilayah>>>>{
        mutableListWilayah = MutableLiveData()
        GlobalScope.launch {
            try {
                val listWilayah = repository.getAllWilayah(token)
                mutableListWilayah.postValue(listWilayah)
            } catch (e : Exception){
                mutableListWilayah.postValue(null)
            }
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

    fun deleteWilayah(token: String, id: String):LiveData<MessageResponse>{
        mutableliveMessageResponseDelete = MutableLiveData()
        GlobalScope.launch {
            val message = repository.deleteWilayahById(token,id)
            mutableliveMessageResponseDelete.postValue(message)
        }
        return mutableliveMessageResponseDelete
    }

    fun getWilayahById(token: String, id:String):LiveData<GlobalWrapperResponse<WilayahWrapper<Wilayah>>>{
        mutableWilayah = MutableLiveData()
        GlobalScope.launch {
            val message = repository.getWilayahById(token,id)
            mutableWilayah.postValue(message)
        }
        return mutableWilayah
    }

    fun updateWilayah(token: String, wilayah: Wilayah, id: String):LiveData<Wilayah>{
        mutableWilayahUpdated = MutableLiveData()
        GlobalScope.launch {
            val message = repository.updateWilayah(token,wilayah,id)
            mutableWilayahUpdated.postValue(message)
        }
        return mutableWilayahUpdated
    }
}