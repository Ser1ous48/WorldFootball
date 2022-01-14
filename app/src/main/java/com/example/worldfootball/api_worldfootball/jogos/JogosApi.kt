package com.example.worldfootball.api_worldfootball.jogos


import com.example.worldfootball.api_worldfootball.dto.JogoDto
import com.example.worldfootball.api_worldfootball.models.Jogos_Models
import retrofit2.Call
import retrofit2.http.*

interface JogosApi {
    @GET("worldfootball/read")
    fun getJogo(@Header("Authorization") token: String): Call<List<Jogos_Models>>

    @FormUrlEncoded
    @POST("worldfootball/create")
    fun createJogo(
        @Header("Authorization") token: String,
        @Field("users_id") users_id: String?,
        @Field("EquipaCasa") EquipaCasa: String,
        @Field("EquipaFora") EquipaFora: String,
        @Field("ResultadoCasa") ResultadoCasa: String?,
        @Field("ResultadoFora") ResultadoFora: String?,
        @Field("AmarelosCasa") AmarelosCasa: String?,
        @Field("AmarelosFora") AmarelosFora: String?,
        @Field("VermelhosCasa") VermelhosCasa: String?,
        @Field("VermelhosFora") VermelhosFora: String?
    ): Call<JogoDto>

    @FormUrlEncoded
    @POST("worldfootball/update")
    fun updateJogo(
        @Header("Authorization") token: String,
        @Field("id") id: Int,
        @Field("EquipaCasa") EquipaCasa: String,
        @Field("EquipaFora") EquipaFora: String,
        @Field("ResultadoCasa") ResultadoCasa: Int,
        @Field("ResultadoFora") ResultadoFora: Int,
        @Field("AmarelosCasa") AmarelosCasa: Int,
        @Field("AmarelosFora") AmarelosFora: Int,
        @Field("VermelhosCasa") VermelhosCasa: Int,
        @Field("VermelhosFora") VermelhosFora: Int
    ): Call<JogoDto>

    @FormUrlEncoded
    @POST("worldfootball/delete")
    fun deleteJogo(@Header("Authorization") token: String, @Field("id") id: Int): Call<JogoDto>
}