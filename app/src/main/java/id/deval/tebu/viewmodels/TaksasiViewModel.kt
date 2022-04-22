package id.deval.tebu.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.request.TaksasiRequest
import id.deval.tebu.db.response.MessageResponse
import id.deval.tebu.db.response.Taksasi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaksasiViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private lateinit var listTaksasi : MutableLiveData<ArrayList<Taksasi>>
    private lateinit var taksasi : MutableLiveData<ArrayList<Taksasi>>
    private lateinit var messageResponse : MutableLiveData<TaksasiRequest>

    fun getTaksasiByUser(token:String):LiveData<ArrayList<Taksasi>>{
        listTaksasi = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getTaksasiByUser(token)
            listTaksasi.postValue(data)
        }
        return listTaksasi
    }

    fun getTaksasiById(token:String,id:String):LiveData<ArrayList<Taksasi>>{
        taksasi = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getTaksasiById(token,id)
            taksasi.postValue(data)
        }
        return taksasi
    }

    fun updateTaksasiUser(token:String,id:String,taksasi: TaksasiRequest):LiveData<TaksasiRequest>{
        messageResponse = MutableLiveData()
        GlobalScope.launch {
            val data = repository.updateTaksasiUser(token,id,taksasi)
            messageResponse.postValue(data)
        }
        return messageResponse
    }
}