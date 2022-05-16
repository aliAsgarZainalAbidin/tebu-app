package id.deval.tebu.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.deval.tebu.db.request.LoginRequest
import id.deval.tebu.db.Repository
import id.deval.tebu.db.response.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private lateinit var mutableUserLogin : MutableLiveData<User>
    private lateinit var mutableUserLogout : MutableLiveData<User>

    fun login(loginRequest: LoginRequest):LiveData<User>{
        mutableUserLogin = MutableLiveData()
        GlobalScope.launch {
            val data = repository.login(loginRequest)
            if (data.status.equals("success")){
                mutableUserLogin.postValue(data.data.user)
            } else {
                mutableUserLogin.postValue(null)
            }
        }
        return mutableUserLogin
    }

    fun logout(id:String, token:String):LiveData<User>{
        mutableUserLogout = MutableLiveData()
        GlobalScope.launch {
            val data = repository.logout(id, token)
            mutableUserLogout.postValue(data)
        }
        return mutableUserLogout
    }

}