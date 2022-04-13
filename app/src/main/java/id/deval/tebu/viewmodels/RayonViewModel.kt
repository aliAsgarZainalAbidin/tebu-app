package id.deval.tebu.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.request.RayonRequest
import id.deval.tebu.db.response.MessageResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RayonViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel() {
    private lateinit var mutableMessageResponse: MutableLiveData<MessageResponse>
    private lateinit var mutableListRayon : MutableLiveData<ArrayList<RayonRequest>>
    private lateinit var mutableRayon : MutableLiveData<RayonRequest>

    fun addRayon(rayonRequest: RayonRequest, token :String):LiveData<MessageResponse>{
        mutableMessageResponse = MutableLiveData()
        GlobalScope.launch {
            val message = repository.addRayon(rayonRequest, token)
            mutableMessageResponse.postValue(message)
        }
        return mutableMessageResponse
    }

    fun getAllRayon(token: String):LiveData<ArrayList<RayonRequest>>{
        mutableListRayon = MutableLiveData()
        GlobalScope.launch {
            val listRayon = repository.getAllrayon(token)
            mutableListRayon.postValue(listRayon)
        }
        return mutableListRayon
    }

    fun getRayonById(token:String, id:String):LiveData<RayonRequest>{
        mutableRayon = MutableLiveData()
        GlobalScope.launch {
            val rayon = repository.getRayonById(token,id)
            mutableRayon.postValue(rayon)
        }
        return mutableRayon
    }

}