package id.deval.tebu.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.Repository
import id.deval.tebu.db.request.TaksasiWithUserRequest
import id.deval.tebu.db.response.GlobalWrapperResponse
import id.deval.tebu.db.response.LaporanWrapper
import id.deval.tebu.db.response.SinderWrapper
import id.deval.tebu.db.response.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaporanViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private lateinit var mutableListSinder: MutableLiveData<GlobalWrapperResponse<SinderWrapper<ArrayList<User>>>>

    fun getListUser(token: String): LiveData<GlobalWrapperResponse<SinderWrapper<ArrayList<User>>>> {
        mutableListSinder = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getListUser(token)
            mutableListSinder.postValue(data)
        }
        return mutableListSinder
    }

    private lateinit var mutableListKebunBySinder: MutableLiveData<GlobalWrapperResponse<LaporanWrapper<ArrayList<TaksasiWithUserRequest>>>>
    fun getKebunBySinder(token: String, id: String): LiveData<GlobalWrapperResponse<LaporanWrapper<ArrayList<TaksasiWithUserRequest>>>> {
        mutableListKebunBySinder = MutableLiveData()
        GlobalScope.launch {
            val data = repository.getKebunBySinder(token, id)
            mutableListKebunBySinder.postValue(data)
        }
        return mutableListKebunBySinder
    }
}