package id.deval.tebu.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.response.Kebun
import id.deval.tebu.db.response.MessageResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
data class KebunViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private lateinit var mutableMessageResponse: MutableLiveData<MessageResponse>
    private lateinit var mutableListKebun : MutableLiveData<ArrayList<Kebun>>
    private lateinit var mutableKebun : MutableLiveData<Kebun>

    fun addKebun(token: String, kebun: Kebun): LiveData<MessageResponse> {
        mutableMessageResponse = MutableLiveData()
        GlobalScope.launch {
            val message = repository.addKebun(token, kebun)
            mutableMessageResponse.postValue(message)
        }
        return mutableMessageResponse
    }

    fun getAllKebun(token: String):LiveData<ArrayList<Kebun>>{
        mutableListKebun = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getAllKebun(token)
            mutableListKebun.postValue(data)
        }
        return mutableListKebun
    }

    fun getKebunById(token: String, id:String):LiveData<Kebun>{
        mutableKebun = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getKebunById(token, id)
            mutableKebun.postValue(data)
        }
        return mutableKebun
    }
}
