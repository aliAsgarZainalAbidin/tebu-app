package id.deval.tebu.db

import android.util.Log
import id.deval.tebu.db.request.*
import id.deval.tebu.db.response.*
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

    suspend fun getListUser(token: String):ArrayList<User>{
        val listUser = arrayListOf<User>()
        val user = apiInterface.getListUser(token)
        listUser.addAll(user)
        return listUser
    }

    suspend fun getKebunBySinder(token: String,id: String):ArrayList<TaksasiWithUserRequest>{
        val listUser = arrayListOf<TaksasiWithUserRequest>()
        val user = apiInterface.getKebunBySinder(token,id)
        listUser.addAll(user)
        return listUser
    }

    suspend fun getSinderById(token: String,id: String):User{
        return apiInterface.getSinderById(token, id)
    }

    suspend fun updateSinder(token: String, user: User, id: String):User{
        return apiInterface.updateSinder(token, user,id)
    }

    suspend fun deleteSinderById(token: String, id: String):MessageResponse{
        return apiInterface.deleteSinderById(token, id)
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

    suspend fun updateRayonById(rayonRequest: RayonRequest,token:String,id:String):RayonRequest{
        return apiInterface.updateRayonById(rayonRequest,token,id)
    }

    suspend fun deleteRayonById(token: String, id: String):MessageResponse{
        return apiInterface.deleteRayonById(token, id)
    }

    suspend fun getAllWilayah(token:String):ArrayList<Wilayah>{
        return apiInterface.getAllWilayah(token)
    }

    suspend fun getWilayahById(token:String,id:String):Wilayah{
        return apiInterface.getWilayahById(token,id)
    }

    suspend fun deleteWilayahById(token: String, id: String):MessageResponse{
        return apiInterface.deleteWilayahById(token, id)
    }

    suspend fun addWilayah(wilayah: Wilayah, token: String):MessageResponse{
        return apiInterface.addWilayah(token, wilayah)
    }

    suspend fun updateWilayah(token: String,wilayah: Wilayah,id: String):Wilayah{
        return apiInterface.updateWilayah(token, wilayah,id)
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

    suspend fun updateKebun(token:String, kebun: Kebun, id: String):Kebun{
        return apiInterface.updateKebun(token,kebun, id)
    }

    suspend fun deleteKebunById(token: String, id: String):MessageResponse{
        return apiInterface.deleteKebunById(token, id)
    }

    suspend fun getTaksasiByUser(token: String):ArrayList<Taksasi>{
        return apiInterface.getTaksasiByUser(token)
    }

    suspend fun getTaksasiById(token: String, id:String):ArrayList<Taksasi>{
        return apiInterface.getTaksasiByUser(token,id)
    }

    suspend fun updateTaksasiUser(token: String, id:String,taksasi: TaksasiRequest):TaksasiRequest{
        return apiInterface.updateTaksasiUser(token,id,taksasi)
    }
}