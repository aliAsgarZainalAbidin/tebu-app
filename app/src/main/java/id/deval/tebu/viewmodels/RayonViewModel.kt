package id.deval.tebu.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.request.RayonRequest
import id.deval.tebu.db.response.GlobalWrapperResponse
import id.deval.tebu.db.response.MessageResponse
import id.deval.tebu.db.response.RayonWrapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RayonViewModel @Inject constructor(
    private val repository: Repository
) :ViewModel() {
    private lateinit var mutableMessageResponse: MutableLiveData<MessageResponse>
    private lateinit var mutableMessageResponseDelete: MutableLiveData<MessageResponse>
    private lateinit var mutableListRayon : MutableLiveData<GlobalWrapperResponse<RayonWrapper<ArrayList<RayonRequest>>>>
    private lateinit var mutableRayon : MutableLiveData<GlobalWrapperResponse<RayonWrapper<RayonRequest>>>
    private lateinit var mutableRayonUpdated : MutableLiveData<RayonRequest>

    fun addRayon(rayonRequest: RayonRequest, token :String):LiveData<MessageResponse>{
        mutableMessageResponse = MutableLiveData()
        GlobalScope.launch {
            val message = repository.addRayon(rayonRequest, token)
            mutableMessageResponse.postValue(message)
        }
        return mutableMessageResponse
    }

    fun deleteRayon(token: String,id: String):LiveData<MessageResponse>{
        mutableMessageResponseDelete = MutableLiveData()
        GlobalScope.launch {
            val message = repository.deleteRayonById(token, id)
            mutableMessageResponseDelete.postValue(message)
        }
        return mutableMessageResponseDelete
    }

    fun getAllRayon(token: String):LiveData<GlobalWrapperResponse<RayonWrapper<ArrayList<RayonRequest>>>>{
        mutableListRayon = MutableLiveData()
        GlobalScope.launch {
            try {
                val listRayon = repository.getAllrayon(token)
                mutableListRayon.postValue(listRayon)
            } catch (e : Exception){
                mutableListRayon.postValue(null)
            }
        }
        return mutableListRayon
    }

    fun getRayonById(token:String, id:String):LiveData<GlobalWrapperResponse<RayonWrapper<RayonRequest>>>{
        mutableRayon = MutableLiveData()
        GlobalScope.launch {
            val rayon = repository.getRayonById(token,id)
            mutableRayon.postValue(rayon)
        }
        return mutableRayon
    }

    fun updateRayodById(rayonRequest: RayonRequest,token:String, id:String):LiveData<RayonRequest>{
        mutableRayonUpdated = MutableLiveData()
        GlobalScope.launch {
            val rayon = repository.updateRayonById(rayonRequest,token,id)
            mutableRayonUpdated.postValue(rayon)
        }
        return mutableRayonUpdated
    }

}