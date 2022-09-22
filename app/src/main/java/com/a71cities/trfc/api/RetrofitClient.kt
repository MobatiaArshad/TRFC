package com.a71cities.trfc.api

import com.a71cities.trfc.BuildConfig
import com.a71cities.trfc.extras.Pref
import com.a71cities.trfc.views.academy.model.AcademyResponse
import com.a71cities.trfc.views.devisions.model.DevisionReponse
import com.a71cities.trfc.views.events.model.MatchResponse
import com.a71cities.trfc.views.gallery.model.GalleryResponse
import com.a71cities.trfc.views.news.model.NewsReponse
import com.a71cities.trfc.views.news.model.ReactionResponse
import com.a71cities.trfc.views.players.models.PlayersResponse
import com.a71cities.trfc.views.signIn.model.SignInResponse
import com.a71cities.trfc.views.signUp.model.SignUpResponse
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface RetrofitClient {

    companion object {
        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(Interceptor { chain ->
                var request = chain.request()
                val urlBuilder = request.url.newBuilder()
                request = request.newBuilder().url(urlBuilder.build()).addHeader("Authorization","Bearer ${Pref.profile?.token}").build()

                try {
                    chain.proceed(request)
                } catch (e: Exception) {
                    e.printStackTrace()
                    val dummy = JSONObject().apply {
                        put("message", "${e.message}")
                        put("url", "$urlBuilder")
                    }.toString()
                    Response.Builder()
                        .code(200)
                        .message("OK")
                        .protocol(Protocol.HTTP_1_1)
                        .request(Request.Builder().url("http://localhost/").build())
                        .message(e.message ?: "")
                        .body(dummy.toResponseBody("text/plain".toMediaType()))
                        .build()
                }
            })
            .addInterceptor(logging)
            .build()


        private fun createRetrofitClient(): RetrofitClient = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()
            .create(RetrofitClient::class.java)

        val apiService: RetrofitClient by lazy { createRetrofitClient() }
    }


    @POST("register")
    suspend fun signUp(
        @Body body: HashMap<String,String>
    ): SignUpResponse

   @POST("login")
    suspend fun signIn(
        @Body body: HashMap<String,String>
    ): SignInResponse

    @GET("teams")
    suspend fun getDivisions(): DevisionReponse

    @GET("players/{id}")
    suspend fun getPLayers(
        @Path("id") id: String
    ): PlayersResponse

    @GET("gallery")
    suspend fun getGallery(): GalleryResponse

    @GET("posts")
    suspend fun getNews(): NewsReponse

    @POST("{reaction}/{id}")
    suspend fun likeUnlike(
        @Path("reaction") reaction: String,
        @Path("id") id: String
    ): ReactionResponse

    @GET("matches")
    suspend fun getMatches(): MatchResponse


    @GET("academy")
    suspend fun getAcademy(): AcademyResponse

}