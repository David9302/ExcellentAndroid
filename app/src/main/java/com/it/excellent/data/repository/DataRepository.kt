package com.it.excellent.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.it.excellent.R
import com.it.excellent.architecture.data.response.DataResult
import com.it.excellent.architecture.data.response.ResponseStatus
import com.it.excellent.architecture.data.response.ResultSource
import com.it.excellent.architecture.domain.request.AsyncTask
import com.it.excellent.common.utils.Utils
import com.it.excellent.data.api.APIS
import com.it.excellent.data.api.AccountService
import com.it.excellent.data.bean.LibraryInfo
import com.it.excellent.data.bean.TestAlbum
import com.it.excellent.data.bean.User
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import org.jetbrains.annotations.Async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

/**
 * 该类其中的数据部分，均为本地Mock.
 */
class DataRepository private constructor(){

    companion object {
        private val S_REQUEST_MANAGER = DataRepository()

        fun getInstance() = S_REQUEST_MANAGER

        private var retrofit:Retrofit? = null
    }

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(APIS.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getFreeMusic(): Observable<DataResult<TestAlbum>> {
        return AsyncTask.doIO { emitter ->
            val gson = Gson()
            val type = object : TypeToken<TestAlbum>(){}.type
            val testAlbum = gson.fromJson<TestAlbum>(Utils.getApp().getString(R.string.free_music_json), type)
            emitter.onNext(DataResult<TestAlbum>(testAlbum, ResponseStatus()))
        }
    }

    fun getLibraryInfo(): Observable<DataResult<List<LibraryInfo>>> {
        return AsyncTask.doIO{ emitter ->
            val gson = Gson()
            val type = object : TypeToken<List<LibraryInfo>>(){}.type
            val testAlbum = gson.fromJson<List<LibraryInfo>>(Utils.getApp().getString(R.string.library_json), type)
            emitter.onNext(DataResult<List<LibraryInfo>>(testAlbum, ResponseStatus()))
        }
    }

    fun downloadFile(): Observable<Int> {
        return AsyncTask.doIO { emitter ->
            val bytes = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
            val bis = ByteArrayInputStream(bytes)
            val bos = ByteArrayOutputStream()
            var b = 0
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    var b: Int
                    while (bis.read().also { b = it } != -1) {
                        delay(500)
                        withContext(Dispatchers.Main) {
                            emitter.onNext(b)
                        }
                    }
                } catch (e: IOException) {
                    withContext(Dispatchers.Main) {
                        emitter.onError(e)
                    }
                } finally {
                    bis.close()
                }
            }
        }
    }

    fun login(user: User): Observable<DataResult<String?>> {
        return AsyncTask.doIO { emitter ->
            val call = retrofit?.create(AccountService::class.java)?.login(user.name, user.password)
            try {
                val response = call!!.execute()
                val responseStatus = ResponseStatus("${response.code()}", response.isSuccessful, ResultSource.NETWORK)
                emitter.onNext(DataResult(response.body(), responseStatus))
            } catch (e: Exception) {
                emitter.onNext(DataResult(null, ResponseStatus(e.message, false, ResultSource.NETWORK)))
            }

        }
    }

}