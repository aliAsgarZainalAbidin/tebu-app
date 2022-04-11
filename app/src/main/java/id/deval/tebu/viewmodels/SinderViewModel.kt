package id.deval.tebu.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.request.SinderRequest
import id.deval.tebu.db.response.MessageResponse
import id.deval.tebu.db.response.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SinderViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private lateinit var mutableMessageResponse: MutableLiveData<MessageResponse>
    private lateinit var mutableListSinder: MutableLiveData<ArrayList<User>>

    fun addSinder(sinderRequest: SinderRequest,token:String):LiveData<MessageResponse>{
        mutableMessageResponse = MutableLiveData()
        GlobalScope.launch {
            val data = repository.addSinder(sinderRequest, token)
            mutableMessageResponse.postValue(data)
        }
        return mutableMessageResponse
    }

    fun getAllSinder(token : String):LiveData<ArrayList<User>>{
        mutableListSinder = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getAllSinder(token)
            mutableListSinder.postValue(data)
        }
        return mutableListSinder
    }
}