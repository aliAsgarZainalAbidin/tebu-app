package id.deval.tebu.db

import android.util.Log
import id.deval.tebu.db.request.LoginRequest
import id.deval.tebu.db.request.RayonRequest
import id.deval.tebu.db.request.SinderRequest
import id.deval.tebu.db.response.Kebun
import id.deval.tebu.db.response.MessageResponse
import id.deval.tebu.db.response.User
import id.deval.tebu.db.response.Wilayah
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

    suspend fun logout(id: String, token: String): User {
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

    suspend fun getSinderById(token: String,id: String):User{
        return apiInterface.getSinderById(token, id)
    }

    suspend fun addRayon(rayonRequest: RayonRequest, token: String):MessageResponse{
        return apiInterface.addRayon(rayonRequest, token)
    }

    suspend fun getAllrayon(token:String):ArrayList<RayonRequest>{
        return apiInterface.getAllRayon(token)
    }

    suspend fun getRayonById(token:String,id:String):RayonRequest{
        return apiInterface.getRayonById(token,id)
    }

    suspend fun getAllWilayah(token:String):ArrayList<Wilayah>{
        return apiInterface.getAllWilayah(token)
    }

    suspend fun getWilayahById(token:String,id:String):Wilayah{
        return apiInterface.getWilayahById(token,id)
    }

    suspend fun addWilayah(wilayah: Wilayah, token: String):MessageResponse{
        return apiInterface.addWilayah(token, wilayah)
    }

    suspend fun addKebun(token:String,kebun: Kebun):MessageResponse{
        return apiInterface.addKebun(token, kebun)
    }

    suspend fun getAllKebun(token:String):ArrayList<Kebun>{
        return apiInterface.getAllKebun(token)
    }

    suspend fun getKebunById(token:String,id:String):Kebun{
        return apiInterface.getKebunById(token,id)
    }
}