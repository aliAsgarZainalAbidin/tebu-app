package id.deval.tebu.retrofit

import id.deval.tebu.db.request.LoginRequest
import id.deval.tebu.db.request.SinderRequest
import id.deval.tebu.db.response.GlobalWrapperResponse
import id.deval.tebu.db.response.MessageResponse
import id.deval.tebu.db.response.User
import retrofit2.http.*


interface ApiInterface {
    //BACKGROUND TREAD (COROUTINE)
//    @GET("api/kasir/menu")
//    suspend fun getMenuAsync(@Header("Authorization") authToken: String, @Query("outlet_id") outlet_id: Int, @Query("nomor_urut") nomor_urut: Int): Response<ModelWrapper<Menu>>
//    @FormUrlEncoded
//    @POST("api/kasir/pelanggan")
//    suspend fun addPelanggan(
//            @Header("Authorization") authToken: String,
//            @Field("unique_id") unique_id: String,
//            @Field("email") email: String?,
//            @Field("nama_pelanggan") nama_pelanggan: String,
//            @Field("no_hp") no_hp: String?,
//            @Field("jk") jk: String?
//    ) : Response<GlobalResult>

//    @GET("api/ongkir/{id}")
//    fun ongkir(
//        @Path("id") idDesa: Int
//    ): Observable<GlobalResult>
//
//    //Kode diatas untuk contoh
//    //Kode untuk aplikasi mulai dari sini
//
//
//
//    @GET("travel/")
//    fun getAllWisata(
//        @Query("page") page:Int
//    ):Observable<ModelListWrapper<WisataSejarah>>
//
//    @GET("travel/{slug}")
//    fun getQR(
//        @Path("slug") slug: String
//    ):Observable<ModelListWrapper<WisataSejarah>>
//
//    @GET("travel/")
//    fun getWisata(
//        @Query("search") search:String
//    ):Observable<ModelListWrapper<WisataSejarah>>

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ):GlobalWrapperResponse<User>

    @GET("logout/{id}")
    suspend fun logout(
        @Path("id") id:Int,
        @Header("token") token:String
    ):User

//    @POST()
//    suspend fun addRayon(
//        @Body rayonRequest:rayonRequest
//    ):

    @POST("sinder")
    suspend fun addSinder(
        @Body sinderRequest: SinderRequest
    ):MessageResponse
}
