package id.deval.tebu.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.request.TaksasiWithUserRequest
import id.deval.tebu.db.response.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaporanViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private lateinit var mutableListSinder: MutableLiveData<ArrayList<User>>

    fun getListUser(token: String): LiveData<ArrayList<User>> {
        mutableListSinder = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getListUser(token)
            mutableListSinder.postValue(data)
        }
        return mutableListSinder
    }

    private lateinit var mutableListKebunBySinder: MutableLiveData<ArrayList<TaksasiWithUserRequest>>
    fun getKebunBySinder(token: String, id: String): LiveData<ArrayList<TaksasiWithUserRequest>> {
        mutableListKebunBySinder = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getKebunBySinder(token, id)
            mutableListKebunBySinder.postValue(data)
        }
        return mutableListKebunBySinder
    }
}