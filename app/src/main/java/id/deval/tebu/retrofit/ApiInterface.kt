package id.deval.tebu.retrofit

import id.deval.tebu.db.request.LoginRequest
import id.deval.tebu.db.request.RayonRequest
import id.deval.tebu.db.request.SinderRequest
import id.deval.tebu.db.response.*
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
    ): GlobalWrapperResponse<User>

    @GET("logout/{id}")
    suspend fun logout(
        @Path("id") id: String,
        @Header("token") token: String
    ): User

    @POST("rayon")
    suspend fun addRayon(
        @Body rayonRequest: RayonRequest,
        @Header("token") token: String
    ): MessageResponse

    @GET("rayon")
    suspend fun getAllRayon(
        @Header("token") token: String
    ): ArrayList<RayonRequest>

    @DELETE("rayon/{id}")
    suspend fun deleteRayonById(
        @Header("token") token: String,
        @Path("id") id:String
    ):MessageResponse

    @GET("rayon/{id}")
    suspend fun getRayonById(
        @Header("token") token: String,
        @Path("id") id: String
    ): RayonRequest

    @PUT("rayon/{id}")
    suspend fun updateRayonById(
        @Body rayonRequest: RayonRequest,
        @Header("token") token: String,
        @Path("id") id: String
    ): RayonRequest

    @POST("sinder")
    suspend fun addSinder(
        @Body sinderRequest: SinderRequest, @Header("token") token: String
    ): MessageResponse

    @GET("sinder")
    suspend fun getAllSinder(
        @Header("token") token: String
    ): GlobalWrapperResponse<ArrayList<User>>

    @GET("sinder/{id}")
    suspend fun getSinderById(
        @Header("token") token: String,
        @Path("id") id: String
    ): User

    @DELETE("sinder/{id}")
    suspend fun deleteSinderById(
        @Header("token") token: String,
        @Path("id") id:String
    ):MessageResponse

    @GET("wilayah")
    suspend fun getAllWilayah(
        @Header("token") token: String
    ): ArrayList<Wilayah>

    @GET("wilayah/{id}")
    suspend fun getWilayahById(
        @Header("token") token: String,
        @Path("id") id: String
    ): Wilayah

    @DELETE("wilayah/{id}")
    suspend fun deleteWilayahById(
        @Header("token") token: String,
        @Path("id") id:String
    ):MessageResponse

    @GET("kebun")
    suspend fun getAllKebun(
        @Header("token") token: String
    ): ArrayList<Kebun>

    @GET("kebun/{id}")
    suspend fun getKebunById(
        @Header("token") token: String,
        @Path("id") id: String
    ): Kebun

    @DELETE("kebun/{id}")
    suspend fun deleteKebunById(
        @Header("token") token: String,
        @Path("id") id:String
    ):MessageResponse

    @POST("wilayah")
    suspend fun addWilayah(
        @Header("token") token: String,
        @Body wilayah: Wilayah
    ): MessageResponse

    @PUT("wilayah/{id}")
    suspend fun updateWilayah(
        @Header("token") token: String,
        @Body wilayah: Wilayah,
        @Path("id") id: String
    ): Wilayah

    @POST("kebun")
    suspend fun addKebun(
        @Header("token") token: String,
        @Body kebun: Kebun
    ): MessageResponse

    @GET("taksasi")
    suspend fun getTaksasiByUser(
        @Header("token") token:String,
    ):ArrayList<Taksasi>

    @GET("taksasi/{id}")
    suspend fun getTaksasiByUser(
        @Header("token") token:String,
        @Path("id") id:String
    ):ArrayList<Taksasi>

    @PUT("taksasi/{id}")
    suspend fun updateTaksasiUser(
        @Header("token") token:String,
        @Path("id") id:String,
        @Body taksasi: Taksasi,
    ):MessageResponse
}
