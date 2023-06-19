package org.d3if3023.temperatureconvert.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3023.temperatureconvert.model.HasilPenemu
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://gist.githubusercontent.com/yennas23/3b4df10ae453f5fd1205eb7c29e7f6c8/raw/f40a1c9846699136876081d01f3c599415ff139f/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CelciusApiService {

    @GET("Penemu.json")
    suspend fun getData(): HasilPenemu
}

object CelciusApi {
    val service: CelciusApiService by lazy {
        retrofit.create(CelciusApiService::class.java)
    }

    fun getCelciusUrl(gambar: String): String {
        return "$BASE_URL$gambar"
    }
}

enum class CelciusStatus {LOADING, SUCCESS, FAILED}
