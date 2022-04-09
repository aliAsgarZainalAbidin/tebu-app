package id.deval.tebu.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.deval.tebu.db.response.User
import id.deval.tebu.retrofit.ApiFactory
import id.deval.tebu.retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    val apiInterface: ApiInterface,
    val database: Database
) {

    suspend fun login(loginRequest:LoginRequest): User? {
        val loginInformation = apiInterface.login(loginRequest)
        return loginInformation.data
    }

    suspend fun logout(id:Int,token:String):User{
        val logout = apiInterface.logout(id, token)
        return logout
    }
}