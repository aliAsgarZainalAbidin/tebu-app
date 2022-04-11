package id.deval.tebu.db

import android.util.Log
import id.deval.tebu.db.request.LoginRequest
import id.deval.tebu.db.request.RayonRequest
import id.deval.tebu.db.request.SinderRequest
import id.deval.tebu.db.response.MessageResponse
import id.deval.tebu.db.response.User
import id.deval.tebu.retrofit.ApiInterface
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    val apiInterface: ApiInterface,
    val database: Database
) {

    suspend fun login(loginRequest: LoginRequest): User? {
        val loginInformation = apiInterface.login(loginRequest)
        return loginInformation.data
    }

    suspend fun logout(id: Int, token: String): User {
        val logout = apiInterface.logout(id, token)
        return logout
    }

    suspend fun addSinder(sinderRequest: SinderRequest, token: String): MessageResponse {
        return apiInterface.addSinder(sinderRequest, token)
    }

    suspend fun getAllSinder(token: String): ArrayList<User> {
        val listUser = arrayListOf<User>()
        val userSinder = apiInterface.getAllSinder(token).data
        listUser.addAll(userSinder as ArrayList)
        return listUser
    }

    suspend fun addRayon(rayonRequest: RayonRequest, token: String):MessageResponse{
        return apiInterface.addRayon(rayonRequest, token)
    }

    suspend fun getAllrayon(token:String):ArrayList<RayonRequest>{
        return apiInterface.getAllRayon(token)
    }
}