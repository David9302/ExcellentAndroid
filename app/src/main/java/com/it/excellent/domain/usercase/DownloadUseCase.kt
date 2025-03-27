package com.it.excellent.domain.usercase

import com.it.excellent.architecture.domain.usecase.UseCase
import com.it.excellent.data.config.Const
import okio.IOException
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class DownloadUseCase : UseCase<DownloadUseCase.RequestValues, DownloadUseCase.ResponseValues>() {

    class RequestValues(val url: String, val path: String) : UseCase.RequestValues

    class ResponseValues(val file: File) : UseCase.ResponseValue

    @Override
    override fun executeUseCase(requestValues: RequestValues?) {
        try {
            val url = URL(requestValues?.url)
            val isteam = url.openStream()
            val file = File(Const.COVER_PATH, requestValues!!.path)
            FileOutputStream(file).use { outputSteam ->
                isteam.use { input ->
                    val buffer = ByteArray(1024)
                    var byteRead: Int = 0
                    while (input.read(buffer).also {
                        byteRead = it
                        } > 0) {
                        outputSteam.write(buffer, 0, byteRead)
                    }
                }
            }
            getUseCaseCallback()!!.onSuccess(ResponseValues(file))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}