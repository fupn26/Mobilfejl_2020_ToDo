package hu.unideb.todo.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ToDoApiService {
    @GET("todos")
    fun getProperties():
            Call<List<ToDoDto>>
}

object ToDoApi {
    val retrofitService : ToDoApiService by lazy {
        retrofit.create(ToDoApiService::class.java) }
}